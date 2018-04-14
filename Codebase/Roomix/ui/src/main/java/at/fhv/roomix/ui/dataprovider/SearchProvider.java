package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.common.ISearchAble;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    private final Object lock = new Object();
    private StringProperty currentQuery = new SimpleStringProperty();
    private ObservableSet<T> queryResultList = FXCollections.observableSet();
    private ISearchAble<T> searchProvider;
    private String nextQuery;
    private boolean inRun;
    private IErrorCall onError;
    private Thread addToSearchQuery;

    public SearchProvider(ISearchAble<T> searchProvider, IErrorCall onError) {
        inRun = true;
        this.searchProvider = searchProvider;
        this.onError = onError;
        nextQuery = "";

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

    private void addToSearchQuery(String toQuery) {
        if (toQuery == null)
            toQuery = "";
        try {
            Thread.sleep(150);
            synchronized (lock) {
                nextQuery = toQuery;
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private void search() {
        while (inRun) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignore) {
            }
            if (nextQuery == null)
                continue;

            String nextQuery = this.nextQuery;
            this.nextQuery = null;
            Future<Collection<T>> future = submit(
                    () -> searchProvider.search(nextQuery));

            try {
                Collection<T> collection = future.get();
                Platform.runLater(() -> {
                    queryResultList.clear();
                    queryResultList.addAll(collection);
                });
            } catch (InterruptedException | ExecutionException e) {
                Platform.runLater(
                        () -> onError.errorOccurred(new Error(e)));
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
