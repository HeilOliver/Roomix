package at.fhv.roomix.ui.view.checkin.content;

import at.fhv.roomix.controller.model.CheckInPojo;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;

public class CheckInTableViewModel implements ViewModel {
    private final ObservableList<CheckInTableRow> reservations =
            FXCollections.observableArrayList();

    private final ObjectProperty<CheckInTableRow> selectedTableRow =
            new SimpleObjectProperty<>();

    @InjectScope
    private ReservationViewScope viewScope;

    public void initialize(){
        selectedTableRow.addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                viewScope.selectedPojoProperty().setValue(null);
            } else {
                viewScope.selectedPojoProperty().setValue(newValue.getPojo());
            }
        }));

        viewScope.getObservableSet().addListener(
                (SetChangeListener<ReservationPojo>) c -> loadData());
    }

    private void loadData(){
        // TODO: implement load data
        reservations.clear();
        viewScope.getObservableSet().forEach(stayPojo -> reservations.add(new CheckInTableRow(stayPojo)));
    }

    public ObservableList<CheckInTableRow> getReservations() {
        return reservations;
    }

    public ObjectProperty<CheckInTableRow> selectedTableRowProperty() {
        return selectedTableRow;
    }
}
