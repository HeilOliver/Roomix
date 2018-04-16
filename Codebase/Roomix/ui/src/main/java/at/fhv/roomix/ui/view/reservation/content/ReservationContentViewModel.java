package at.fhv.roomix.ui.view.reservation.content;

import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import com.mysql.cj.api.x.View;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.content
 * ReservationContentViewModel
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationContentViewModel implements ViewModel {

    @InjectScope
    private ReservationViewScope viewScope;

    private BooleanProperty detailOpenProperty = new SimpleBooleanProperty();

    public void initialize() {
        viewScope.selectedPojoProperty().addListener(((observable, oldValue, newValue) -> {
            detailOpenProperty.setValue(newValue != null);
        }));

    }

    ReadOnlyBooleanProperty detailOpenProperty() {
        return detailOpenProperty;
    }
}