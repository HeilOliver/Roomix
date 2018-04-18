package at.fhv.roomix.ui.view.reservation.edit.scope;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.view.contact.ContactView;
import at.fhv.roomix.ui.view.reservation.edit.item.AbstractItemHandler;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.scope
 * ReservationEditScope
 * 17/04/2018 Oliver
 * <p>
 * An Scope for the Reservation Edit view.
 */
public class ReservationEditScope extends AbstractItemHandler implements Scope {

    private ObjectProperty<ItemControlViewModel<ContactPojo>>
            contractingParty = new SimpleObjectProperty<>();

    public ObjectProperty<ItemControlViewModel<ContactPojo>> getContractingPartyProperty() {
        return contractingParty;
    }

    public void newContractParty() {
        ItemControlViewModel<ContactPojo> itemControl
                = new ItemControlViewModel<>(this, ContactView.class, (pojo) ->
                String.format("%s - %s - %s", pojo.getFirstName(), pojo.getLastName(), pojo.getPlace()));
        contractingParty.setValue(itemControl);
    }

    //region DeleteClose
    @Override
    protected void deleteItemControl(ItemControlViewModel itemControl) {
        if (itemControl.equals(contractingParty.get()))
            contractingParty.setValue(null);
    }

    public void dispose() {
        if (contractingParty.get() != null)
            contractingParty.get().dispose();

    }
    //endregion
}
