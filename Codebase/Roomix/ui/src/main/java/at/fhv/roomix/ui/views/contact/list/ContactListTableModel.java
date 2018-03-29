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
    private String forename;
    private String surname;
    private String companyName;

    private String street;
    private String place;
    private String postcode;

    private ContactPojo pojo;

    public ContactPojo getPojo() {
        return pojo;
    }

    public ContactListTableModel(ContactPojo pojo) {
        // TODO don´t copy read directly
        this.pojo = pojo;
        forename = pojo.getForename();
        surname = pojo.getSurname();
        companyName = pojo.getCompanyName();
        street = pojo.getStreet();
        place = pojo.getPlace();
        postcode = pojo.getPostcode();
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getStreet() {
        return street;
    }

    public String getPlace() {
        return place;
    }

    public String getPostcode() {
        return postcode;
    }
}
