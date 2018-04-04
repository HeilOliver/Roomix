package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Roomix
 * at.fhv.roomix.persist.model
 * CreditCardEntity
 * 24/03/2018 Oliver
 * <p>
 * Enter Description here
 */
@Entity
@Table(name = "CreditCard", schema = "Roomix")
public class CreditCardEntity {
    private int creditCardId;
    private String number;
    private String owner;
    private String type;
    private Date validDate;
    private ContactEntity contactByContact;

    @Id
    @Column(name = "CreditCardID")
    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    @Basic
    @Column(name = "Number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "Owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "ValidDate")
    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardEntity that = (CreditCardEntity) o;
        return creditCardId == that.creditCardId &&
                Objects.equals(number, that.number) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(type, that.type) &&
                Objects.equals(validDate, that.validDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(creditCardId, number, owner, type, validDate);
    }

    @ManyToOne
    @JoinColumn(name = "Contact", referencedColumnName = "ContactID")
    public ContactEntity getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(ContactEntity contactByContact) {
        this.contactByContact = contactByContact;
    }
}
