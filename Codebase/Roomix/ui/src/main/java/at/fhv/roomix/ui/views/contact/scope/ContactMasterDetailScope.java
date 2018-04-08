package at.fhv.roomix.ui.views.contact.scope;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.scope
 * ContactMasterDetailScope
 * 08/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactMasterDetailScope {

    private final ObjectProperty<ContactPojo> selectedContact =
            new SimpleObjectProperty<>();

    public ContactPojo getSelectedContact() {
        return selectedContact.get();
    }

    public ObjectProperty<ContactPojo> selectedContactProperty() {
        return selectedContact;
    }
}
