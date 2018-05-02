package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.common.exceptions.FaultException;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.reservation.model.ArrangementPojo;
import at.fhv.roomix.controller.reservation.model.PricePojo;
import at.fhv.roomix.controller.reservation.model.ReservationUnitPojo;
import at.fhv.roomix.controller.reservation.model.RoomCategoryPojo;
import at.fhv.roomix.ui.common.ICallable;
import at.fhv.roomix.ui.common.ICallableWithParameter;
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
 * ReservationUnitProvider
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationUnitProvider extends AbstractProvider {

    private ObservableList<RoomCategoryPojo> possibleCategories = FXCollections.observableArrayList();
    private ObservableList<ArrangementPojo> possibleArrangements = FXCollections.observableArrayList();

    private BooleanProperty inLoadCategories = new SimpleBooleanProperty();
    private BooleanProperty inLoadArrangements = new SimpleBooleanProperty();

    public void loadCategories(LocalDate from, LocalDate till, ContactPojo contractingParty, ICallable onSuccess) {
        if (from == null || till == null) return;
        // TODO Das ist nicht ThreadSave!
        submit(() -> {
            Platform.runLater(() -> inLoadCategories.setValue(true));
            IReservationController instance =
                    ReservationControllerFactory.getInstance();
            try {
                Collection<RoomCategoryPojo> collection =
                        instance.getRoomAllocation(LoginProvider.getSessionID(),
                        from, till, contractingParty);
                Platform.runLater(() -> {
                    possibleCategories.clear();
                    possibleCategories.addAll(collection);
                    onSuccess.call();
                });
            } catch (Exception e) {
                LOG.debug(e.getMessage());
                e.printStackTrace();
            } finally {
                Platform.runLater(() -> inLoadCategories.setValue(false));
            }
        });
    }

    public void loadArrangements(ICallable onSuccess) {
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
                // Wait here a little bit
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(onSuccess::call);
            } catch (FaultException e) {
                LOG.debug(e.getMessage());
            } finally {
                Platform.runLater(() -> inLoadArrangements.setValue(false));
            }
        });
    }

    public void calculatePrice(ICallableWithParameter<PricePojo> onSuccess,
                               ReservationUnitPojo pojo, ContactPojo contractingParty) {
        submit(() -> {
            IReservationController instance =
                    ReservationControllerFactory.getInstance();
            try {
                PricePojo price =
                        instance.getPrice(LoginProvider.getSessionID(), pojo, contractingParty);
                Platform.runLater(() -> {
                    onSuccess.call(price);
                });
            } catch (Exception e) {
                LOG.debug(e.getMessage());
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
