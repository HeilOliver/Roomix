package at.fhv.roomix.ui.views.contact.scope;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.scope
 * ContactViewScope
 * 08/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactViewScope implements Scope {
    public static final String NEW = "contact_new";
    public static final String EDIT = "contact_edit";
    public static final String CLOSE = "contact_edit_close";
    public static final String ARCHIVE = "contact_archive";

    private final ObjectProperty<ContactPojo> inEdit
            = new SimpleObjectProperty<>();

    public ObjectProperty<ContactPojo> inEditProperty() {
        return inEdit;
    }

    private final ObjectProperty<ContactPojo> selectedContact =
            new SimpleObjectProperty<>();

    public ContactPojo getSelectedContact() {
        return selectedContact.get();
    }

    public ObjectProperty<ContactPojo> selectedContactProperty() {
        return selectedContact;
    }
}
