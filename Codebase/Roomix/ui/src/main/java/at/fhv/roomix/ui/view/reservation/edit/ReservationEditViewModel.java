package at.fhv.roomix.ui.view.reservation.edit;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import at.fhv.roomix.ui.view.reservation.edit.scope.ReservationEditScope;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.Parent;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit
 * ReservationEditViewModel
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
@ScopeProvider(scopes = ReservationEditScope.class)
public class ReservationEditViewModel implements ViewModel {


    @InjectScope
    private ReservationViewScope viewScope;
    @InjectScope
    private ReservationEditScope editScope;

    public void initialize() {

        viewScope.subscribe(ContactViewScope.commandEditView, ((key, payload) -> reLoad()));
        viewScope.subscribe(ContactViewScope.commandContentView, ((key, payload) -> close()));


        viewScope.subscribe(ContactViewScope.commandCommitEdit, (key, payload) -> {

        });
    }

    private void close() {
        editScope.dispose();
    }

    private void reLoad() {

    }

}
