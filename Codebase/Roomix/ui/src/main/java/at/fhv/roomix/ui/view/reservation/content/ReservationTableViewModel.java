package at.fhv.roomix.ui.view.reservation.content;


import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.ui.view.reservation.scope.EDataProvider;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.content
 * ReservationTableViewModel
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationTableViewModel implements ViewModel {

    private final ObservableList<ReservationTableRowModel> contacts =
            FXCollections.observableArrayList();

    private final ObjectProperty<ReservationTableRowModel> selectedTableRow =
            new SimpleObjectProperty<>();

    @InjectScope
    private  ReservationViewScope viewScope;

    public void initialize() {
        viewScope.init(EDataProvider.ReservationProvider);
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

    private void loadData() {
        contacts.clear();
        viewScope.getObservableSet()
                .forEach(reservationPojo
                        -> contacts.add(new ReservationTableRowModel(
                        reservationPojo)));
    }

    ObservableList<ReservationTableRowModel> getContacts() {
        return contacts;
    }

    ObjectProperty<ReservationTableRowModel> selectedTableRowProperty() {
        return selectedTableRow;
    }

}
