package at.fhv.roomix.ui.view.reservation.edit;


import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit
 * ReservationEditScope
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationEditScope implements Scope {

    private ObjectProperty<ItemControlViewModel<ContactPojo>> currContractingParty = new SimpleObjectProperty<>();

    public ItemControlViewModel<ContactPojo> getCurrContractingParty() {
        return currContractingParty.get();
    }

    public ContactPojo getContractingParty() {
        if (currContractingParty.get() == null) return null;
        return currContractingParty.get().getPojo();
    }

    public ObjectProperty<ItemControlViewModel<ContactPojo>> currContractingPartyProperty() {
        return currContractingParty;
    }
}
