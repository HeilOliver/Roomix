package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.controller.reservation.model.ContactPojo;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactListTableModel
 * 26/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactListTableModel {
    private ContactPojo pojo;

    ContactPojo getPojo() {
        return pojo;
    }

    public ContactListTableModel(ContactPojo pojo) {
        this.pojo = pojo;
    }

    public String getFirstname() {
        return pojo.getFirstName();
    }

    public String getLastname() {
        return pojo.getLastName();
    }

    public String getCompanyName() {
        return pojo.getCompanyName();
    }

    public String getPhonenumber() { return pojo.getPhoneNumber(); }

    public String getStreet() {
        return pojo.getStreet();
    }

    public String getPlace() {
        return pojo.getPlace();
    }

    public String getPostcode() {
        return pojo.getPostcode();
    }

    public String getCountry() {
        return pojo.getCountry();
    }

    public String getEmail() {
        return pojo.getEmail();
    }
}
