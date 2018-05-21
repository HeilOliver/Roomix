package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.common.exceptions.FaultException;
import at.fhv.roomix.controller.common.exceptions.GetFault;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.model.*;
import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.ui.common.ICallable;
import at.fhv.roomix.ui.common.ICallableWithParameter;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

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

    public void loadCategories(ContactPojo contractingParty, ICallable onSuccess) {
        // TODO Das ist nicht ThreadSave!
        submit(() -> {
            Platform.runLater(() -> inLoadCategories.setValue(true));
            IReservationController instance =
                    ReservationControllerFactory.getInstance();
            try {
                // TODO: get price and occupation data and send it to UI
                Collection<RoomCategoryPojo> collection = instance.getAllCategory(LoginProvider.getSessionID());
//                Collection<CategoryDataPojo> collection =
//                        instance.calculateData(LoginProvider.getSessionID(), null, contractingParty, from, till);
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
            } catch (SessionFaultException e) {
                LOG.debug(e.getMessage());
            } catch (GetFault getFault) {
                // TODO Fix exception Handling
                getFault.printStackTrace();
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
                //TODO: get Price data and send back to UI
                PricePojo price =
                        instance.calculatePrice(LoginProvider.getSessionID(), pojo, contractingParty);
                Platform.runLater(() -> {
                    onSuccess.call(price);
                });
            } catch (Exception e) {
                // TODO Fix Error Handling
                LOG.debug(e.getMessage());
            }
        });
    }

    public void calculateOccupationForCategory(ICallableWithParameter<CategoryDataPojo> onSuccess, RoomCategoryPojo roomCategory,
                                               ContactPojo contractingParty, LocalDate from, LocalDate till){
        if(from == null || till == null) return;
        submit(() -> {
            IReservationController instance =
                    ReservationControllerFactory.getInstance();
            try {
                //TODO: get Price data and send back to UI
                Collection<CategoryDataPojo> categoryDataPojos = instance.calculateData(LoginProvider.getSessionID(), roomCategory, contractingParty, from, till);
                Optional<CategoryDataPojo> optionalDataPojo = categoryDataPojos.stream().min(Comparator.comparingInt(CategoryDataPojo::getFree));
                if(optionalDataPojo.isPresent()){
                    CategoryDataPojo categoryDataPojo = optionalDataPojo.get();
                    Platform.runLater(() -> {
                        onSuccess.call(categoryDataPojo);
                    });
                }
            } catch (Exception e) {
                // TODO Fix Error Handling
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
