package at.fhv.roomix.ui.view.reservation.edit.contact;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.ui.common.AbstractTableRowModel;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.content
 * ContactTableRowModel
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactTableRowModel extends AbstractTableRowModel<ContactPojo> {

    public ContactTableRowModel(ContactPojo pojo) {
        super(pojo);
    }

    public ContactPojo getPojo() {
        return pojo;
    }

    public String getFirstName() {
        return pojo.getFirstName();
    }

    public String getLastName() {
        return pojo.getLastName();
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
