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
    private String firstname;
    private String lastname;
    private String companyName;
    private String phonenumber;
    private String street;
    private String place;
    private String postcode;
    private String country;
    private String email;


    private ContactPojo pojo;

    public ContactPojo getPojo() {
        return pojo;
    }

    public ContactListTableModel(ContactPojo pojo) {
        // TODO don´t copy read directly
        this.pojo = pojo;
        firstname = pojo.getFirstname();
        lastname = pojo.getLastname();
        companyName = pojo.getCompanyName();
        phonenumber = pojo.getPhoneNumber();
        street = pojo.getStreet();
        place = pojo.getPlace();
        postcode = pojo.getPostcode();
        country = pojo.getCountry();
        email = pojo.getEmail();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhonenumber() { return phonenumber; }

    public String getStreet() {
        return street;
    }

    public String getPlace() {
        return place;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }



}
