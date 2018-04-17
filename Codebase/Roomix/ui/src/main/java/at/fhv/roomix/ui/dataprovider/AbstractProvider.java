package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.ui.common.CloseEvent;
import at.fhv.roomix.ui.common.ICallable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * AbstractProvider
 * 14/04/2018 Oliver
 * <p>
 * An Abstract Provider Class. It provides the {@code inProcess} Property and executes all outstanding tasks
 * to the controller level
 */
public abstract class AbstractProvider {
    protected static final Logger LOG = LoggerFactory.getLogger(AbstractProvider.class);
    // May here to more threads??
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final BooleanProperty inProcess = new SimpleBooleanProperty();
    private static HashSet<Object> runningThreads = new HashSet<>();
    private static List<ICallable> onShutdownCallback = new ArrayList<>();

    protected static ReadOnlyBooleanProperty inProcessProperty() {
        return inProcess;
    }

    protected static void submit(Runnable runnable) {
        if (runnable == null) return;
        try {
            executor.submit(() -> {
                Object inRun = new Object();
                runningThreads.add(inRun);
                synchronized (inProcess) {
                    inProcess.setValue(true);
                }
                try {
                    runnable.run();
                } catch (Exception ex) {
                    LOG.debug("Exception is thrown while executing runnable - " + ex.getMessage());
                }
                runningThreads.remove(inRun);
                if (runningThreads.isEmpty())
                    synchronized (inProcess) {
                        inProcess.setValue(false);
                    }
            });
        } catch (RejectedExecutionException ex) {
            LOG.debug("Executor is not ready - " + ex.getMessage());
        }
    }

    protected static <T> Future<T> submit(Callable<T> callable) {
        if (callable == null) return null;
        try {
            return executor.submit(() -> {
                Object inRun = new Object();
                runningThreads.add(inRun);
                synchronized (inProcess) {
                    inProcess.setValue(true);
                }
                try {
                    return callable.call();
                } catch (Exception ex) {
                    LOG.debug("Exception is thrown while executing runnable - " + ex.getMessage());
                } finally {
                    runningThreads.remove(inRun);
                    if (runningThreads.isEmpty())
                        synchronized (inProcess) {
                            inProcess.setValue(false);
                        }
                }
                return null;
            });
        } catch (RejectedExecutionException ex) {
            LOG.debug("Executor is not ready - " + ex.getMessage());
        }
        return null;
    }

    protected static void onShutdown(ICallable callable) {
        if (callable == null) return;
        onShutdownCallback.add(callable);
    }

    /**
     * The shutdown of the application can be triggered by firing the
     * {@link at.fhv.roomix.ui.common.CloseEvent} CDI event.
     */
    public static void triggerShutdown(@Observes CloseEvent event) {
        for (ICallable callable : onShutdownCallback) {
            try {
                callable.call();
            } catch (Exception e) {
                LOG.debug("Shutdown Callback Exception - " + e.getMessage());
            }
        }
        executor.shutdown();
    }
}
