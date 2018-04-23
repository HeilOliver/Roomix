package at.fhv.roomix.domain.guest.model;

import java.sql.Date;

public class CreditCardDomain {

    private int creditCardId;
    private String cardNumber;
    private String cardOwner;
    private String cardType;
    private Date validDate;
    private Integer contact;

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getNumber() {
        return cardNumber;
    }

    public void setNumber(String number) {
        this.cardNumber = number;
    }

    public String getOwner() {
        return cardOwner;
    }

    public void setOwner(String owner) {
        this.cardOwner = owner;
    }

    public String getType() {
        return cardType;
    }

    public void setType(String type) {
        this.cardType = type;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }
}
