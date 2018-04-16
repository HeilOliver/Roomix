package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.common.ISearchAble;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * SearchProvider
 * 14/04/2018 Oliver
 * <p>
 * The Search Provider offers an simple solution for search in an
 * {@code ISearchAble} provider.
 */
public abstract class SearchProvider<T> extends AbstractProvider {
    private final AtomicReference<String> nextQuery;
    protected IErrorCall onError;
    private StringProperty currentQuery = new SimpleStringProperty();
    private ObservableSet<T> queryResultList = FXCollections.synchronizedObservableSet(
            FXCollections.observableSet(new HashSet<>()));
    private ISearchAble<T> searchProvider;
    private String lastQuery;
    private boolean inRun;
    private Thread addToSearchQuery;

    SearchProvider(ISearchAble<T> searchProvider) {
        inRun = true;
        this.searchProvider = searchProvider;
        this.onError = (e) -> {
        };
        nextQuery = new AtomicReference<>("");

        onShutdown(() -> inRun = false);

        Thread searchThread = new Thread(this::search);
        searchThread.setDaemon(true);
        searchThread.start();

        currentQuery.addListener(((observable, oldValue, newValue) -> {
            if (addToSearchQuery != null)
                addToSearchQuery.interrupt();
            addToSearchQuery = new Thread(() -> addToSearchQuery(newValue));
            addToSearchQuery.start();
        }));
    }

    public void addErrorCallBack(IErrorCall errorCall) {
        if (errorCall == null)
            errorCall = (e) -> {
            };
        onError = errorCall;
    }

    private void addToSearchQuery(String toQuery) {
        if (toQuery == null)
            toQuery = "";
        try {
            Thread.sleep(150);
            synchronized (nextQuery) {
                nextQuery.set(toQuery);
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    protected void reSearch() {
        synchronized (nextQuery) {
            nextQuery.set(lastQuery);
        }
    }

    private void search() {
        while (inRun) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignore) {
            }
            if (nextQuery.get() == null)
                continue;

            Future<Collection<T>> future;
            synchronized (nextQuery) {
                String localQuery = nextQuery.get();
                nextQuery.set(null);
                lastQuery = localQuery;
                future = submit(
                        () -> searchProvider.search(localQuery));
            }

            if (future == null) {
                Platform.runLater(
                        () -> onError.errorOccurred(
                                new Error("Internal Error")));
                continue;
            }

            try {
                Collection<T> collection = future.get();
                Platform.runLater(() -> {
                    if (collection == null) {
                        LOG.debug("Future returned empty collection");
                        return;
                    }
                    queryResultList.clear();
                    // TODO Remove here
//                    Object[] objects = queryResultList.toArray();
//                    for (Object object : objects) {
//                        queryResultList.remove(object);
//                    }
                    queryResultList.addAll(collection);
                });
            } catch (InterruptedException | ExecutionException e) {
                Platform.runLater(
                        () -> onError.errorOccurred(new Error(e.getMessage())));
            }
        }
    }

    public ObservableSet<T> getQueryResultSet() {
        return queryResultList;
    }

    public StringProperty currentQueryProperty() {
        return currentQuery;
    }

}
