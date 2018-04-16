package at.fhv.roomix.ui.view.reservation;

import at.fhv.roomix.ui.common.AbstractToolbar;
import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import com.sun.glass.ui.View;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation
 * ReservationToolbarViewModel
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationToolbarViewModel extends AbstractToolbar<ReservationViewScope> implements ViewModel {

    @InjectScope
    private ReservationViewScope viewScope;

    public void initialize() {
        super.initialize(viewScope);
    }


}
