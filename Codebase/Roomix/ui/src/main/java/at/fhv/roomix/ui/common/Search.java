package at.fhv.roomix.ui.common;

import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.config.SessionProvider;
import at.fhv.roomix.ui.views.contact.edit.ContactEditViewModel;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import javax.swing.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static at.fhv.roomix.ui.views.contact.edit.ContactEditViewModel.*;

/**
 * Roomix
 * at.fhv.roomix.ui.common
 * Search
 * 13/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Search<T> {


    public ObservableBooleanValue getInProcessProperty() {
        return inProcess;
    }

    public interface ISearchAble<T> {
        Collection<T> search(String query) throws SessionFaultException;
    }

    private StringProperty currentQuery = new SimpleStringProperty();
    private ObservableSet<T> queryResultList = FXCollections.observableSet();
    private ISearchAble<T> searchProvider;
    private ICallable onError;
    private String nextQuery;
    private final Object lock = new Object();

    public Search(ISearchAble<T> searchProvider, ICallable onError) {
        this.searchProvider = searchProvider;
        this.onError = onError;
        nextQuery = "";

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
    private Thread addToSearchQuery;
    private void addToSearchQuery(String toQuery) {
        if (toQuery == null)
            toQuery = "";
        try {
            Thread.sleep(500);
            synchronized (lock) {
                nextQuery = toQuery;
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    private BooleanProperty inProcess = new SimpleBooleanProperty();

    private void search() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignore) {
            }
            if (nextQuery == null)
                continue;

            Platform.runLater(() -> inProcess.setValue(true));
            try {
                String nextQuery = this.nextQuery;
                this.nextQuery = null;
                Collection<T> search = searchProvider.search(nextQuery);

                Platform.runLater(() -> {
                    queryResultList.clear();
                    queryResultList.addAll(search);
                });
            } catch (Exception e) {
                if (onError != null)
                    Platform.runLater(onError::call);
            } finally {
                Platform.runLater(() -> inProcess.setValue(false));
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
