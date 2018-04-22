package at.fhv.roomix.ui.view.reservation.edit;


import at.fhv.roomix.controller.contact.model.ContactPojo;
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

    private ObjectProperty<ContactPojo> currContractingParty = new SimpleObjectProperty<>();

    public ContactPojo getCurrContractingParty() {
        return currContractingParty.get();
    }

    public ObjectProperty<ContactPojo> currContractingPartyProperty() {
        return currContractingParty;
    }
}
