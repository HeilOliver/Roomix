package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactListViewModel
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
@Singleton
public class ContactListViewModel implements ViewModel {

    private final ObservableList<ContactListTableModel> contacts = FXCollections.observableArrayList();
    private final ObjectProperty<ContactListTableModel> selectedTableRow = new SimpleObjectProperty<>();
    private final BooleanProperty contactSelected = new SimpleBooleanProperty();

    public ContactListViewModel() {
        selectedTableRow.addListener(((observable, oldValue, newValue) -> {
            contactSelected.setValue(newValue != null);
        }));

        ContactPojo pojo = new ContactPojo();
        pojo.setForename("Oliver");
        pojo.setSurname("Heil");
        pojo.setCountry("Germany");
        pojo.setPhoneNumber("+4312132132132");
        pojo.setEmail("Some@some.com");
        pojo.setPlace("Dornbirn");
        pojo.setPostcode("8505");
        pojo.setStreet("SomeStreet 4");
        pojo.setActive((byte) 0);

        contacts.add(new ContactListTableModel(pojo));
    }

    public BooleanProperty contactSelectedProperty() {
        return contactSelected;
    }

    public ObservableList<ContactListTableModel> getContacts() {
        return contacts;
    }

    public ObjectProperty<ContactListTableModel> selectedTableRowProperty() {
        return selectedTableRow;
    }
}
