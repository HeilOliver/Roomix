package at.fhv.roomix.ui.view.reservation;

import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import com.mysql.cj.api.x.View;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation
 * ReservationViewModel
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
@ScopeProvider(scopes = ReservationViewScope.class)
public class ReservationViewModel implements ViewModel {

    private BooleanProperty contentViewVisible = new SimpleBooleanProperty();
    private BooleanProperty editViewVisible = new SimpleBooleanProperty();

    @InjectScope
    private ReservationViewScope viewScope;

    public void initialize() {
        viewScope.subscribe(ContactViewScope.commandContentView, (key, payload) -> showContent());
        viewScope.subscribe(ContactViewScope.commandEditView, (key, payload) -> showEdit());
        showContent();
    }

    private void showContent() {
        contentViewVisible.setValue(true);
        editViewVisible.setValue(false);
    }

    private void showEdit() {
        contentViewVisible.setValue(false);
        editViewVisible.setValue(true);
    }

    BooleanProperty contentViewVisibleProperty() {
        return contentViewVisible;
    }

    BooleanProperty editViewVisibleProperty() {
        return editViewVisible;
    }
}
