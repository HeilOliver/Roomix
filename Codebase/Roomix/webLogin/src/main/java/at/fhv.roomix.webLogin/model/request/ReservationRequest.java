package at.fhv.roomix.webLogin.model.request;

import java.util.LinkedList;

public class ReservationRequest {
    private String fname;
    private String lname;
    private String eMail;
    private String postCode;
    private String place;
    private String country;
    private String phoneNumber;
    private String street;
    private String housenumber;
    private String creditcard;
    private String getStartDate;
    private String getEndDate;
    private CategoryRequest[] categoryRequests;

    public CategoryRequest[] getCategoryRequests() {
        return categoryRequests;
    }

    public void setCategoryRequests(CategoryRequest[] categoryRequests) {
        this.categoryRequests = categoryRequests;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }

    public String getGetStartDate() {
        return getStartDate;
    }

    public void setGetStartDate(String getStartDate) {
        this.getStartDate = getStartDate;
    }

    public String getGetEndDate() {
        return getEndDate;
    }

    public void setGetEndDate(String getEndDate) {
        this.getEndDate = getEndDate;
    }
}
