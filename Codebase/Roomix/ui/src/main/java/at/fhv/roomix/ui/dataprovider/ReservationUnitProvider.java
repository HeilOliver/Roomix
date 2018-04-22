package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.reservation.model.ArrangementPojo;
import at.fhv.roomix.controller.reservation.model.RoomCategoryPojo;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * ReadOnlyReservationProvider
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReadOnlyReservationProvider extends AbstractProvider {

    private ObservableList<RoomCategoryPojo> possibleCategories = FXCollections.observableArrayList();
    private ObservableList<ArrangementPojo> possibleArrangements = FXCollections.observableArrayList();

    private BooleanProperty inLoadCategories = new SimpleBooleanProperty();
    private BooleanProperty inLoadArrangements = new SimpleBooleanProperty();

    public void loadCategories(LocalDate from, LocalDate till, ContactPojo contractingParty) {
        if (from == null || till == null) return;
        // TODO Das ist nicht ThreadSave!
        submit(() -> {
            Platform.runLater(() -> inLoadCategories.setValue(true));
            IReservationController instance =
                    ReservationControllerFactory.getInstance();
            try {
                Collection<RoomCategoryPojo> collection =
                        instance.getSearchedCategory(LoginProvider.getSessionID(),
                                from, till, contractingParty);
                Platform.runLater(() -> {
                    possibleCategories.clear();
                    possibleCategories.addAll(collection);
                });
            } catch (SessionFaultException e) {
                LOG.debug(e.getMessage());
            } finally {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> inLoadCategories.setValue(false));
            }
        });
    }

    public void loadArticles() {
        submit(() -> {
            Platform.runLater(() -> inLoadArrangements.setValue(true));
            IReservationController instance =
                    ReservationControllerFactory.getInstance();
            try {
                Collection<ArrangementPojo> collection =
                        instance.getAllArrangement(LoginProvider.getSessionID());
                Platform.runLater(() -> {
                    possibleArrangements.clear();
                    possibleArrangements.addAll(collection);
                });
            } catch (SessionFaultException e) {
                LOG.debug(e.getMessage());
            } finally {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> inLoadArrangements.setValue(false));
            }
        });
    }

    public ObservableList<RoomCategoryPojo> getPossibleCategories() {
        return possibleCategories;
    }

    public ObservableList<ArrangementPojo> getPossibleArrangements() {
        return possibleArrangements;
    }

    public void clear() {
        possibleCategories.clear();
        possibleArrangements.clear();
    }

    public BooleanProperty inLoadCategoriesProperty() {
        return inLoadCategories;
    }

    public BooleanProperty inLoadArrangementsProperty() {
        return inLoadArrangements;
    }
}
