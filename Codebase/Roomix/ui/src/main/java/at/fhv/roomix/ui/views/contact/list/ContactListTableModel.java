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

    public ContactListTableModel(ContactPojo pojo) {
        this.pojo = pojo;
    }

    public ContactPojo getPojo() {
        return pojo;
    }

    public String getFname() {
        return pojo.getFname();
    }

    public String getLname() {
        return pojo.getLname();
    }

    public String getHouseNumber() {
        return pojo.getHouseNumber();
    }

    public String getCompanyName() {
        return pojo.getCompanyName();
    }

    public String getPhonenumber() {
        return pojo.getPhoneNumber();
    }

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
