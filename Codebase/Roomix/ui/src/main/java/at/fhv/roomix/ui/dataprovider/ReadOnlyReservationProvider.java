package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.RoomCategoryPojo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

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

    public void loadCategories(LocalDate from, LocalDate till, ContactPojo contractingParty) {
        possibleCategories.clear();
        new Thread(() -> {
            RoomCategoryPojo CatA = new RoomCategoryPojo();
            CatA.setFree(10);
            CatA.setOccupied(33);
            CatA.setQuota(5);
            CatA.setUnconfirmedReservation(6);
            CatA.setId(100);
            CatA.setDescription("Cat A");

            RoomCategoryPojo CatB = new RoomCategoryPojo();
            CatB.setFree(3);
            CatB.setOccupied(64);
            CatB.setQuota(22);
            CatB.setUnconfirmedReservation(54);
            CatB.setId(100);
            CatB.setDescription("Cat A");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                possibleCategories.add(CatA);
                possibleCategories.add(CatB);
            });
        }).start();
    }

    public ObservableList<RoomCategoryPojo> getPossibleCategories() {
        return possibleCategories;
    }
}
