package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import javafx.beans.property.ReadOnlyBooleanProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * ContactProvider
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactProvider extends AbstractSearchEditProvider<ContactPojo> {

    public ContactProvider() {
        super(
                query -> ReservationControllerFactory.getInstance()
                        .getSearchedContacts(LoginProvider.getSessionID(), query),
                update -> ReservationControllerFactory.getInstance().updateContact(
                        LoginProvider.getSessionID(), update)
        );
    }

    public ReadOnlyBooleanProperty getInProcessProperty() {
        return inProcessProperty();
    }
}
