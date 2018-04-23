package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "CreditCard", schema = "Roomix", catalog = "")
public class CreditCardEntity {
    private int creditCardId;
    private String cardNumber;
    private String cardOwner;
    private String cardType;
    private Date validDate;
    private Integer contact;
    private ContactEntity contactByContact;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CreditCardID")
    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    @Basic
    @Column(name = "CardNumber")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Basic
    @Column(name = "CardOwner")
    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    @Basic
    @Column(name = "CardType")
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Basic
    @Column(name = "ValidDate")
    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    @Basic
    @Column(name = "Contact", insertable = false, updatable = false)
    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardEntity that = (CreditCardEntity) o;
        return creditCardId == that.creditCardId &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(cardOwner, that.cardOwner) &&
                Objects.equals(cardType, that.cardType) &&
                Objects.equals(validDate, that.validDate) &&
                Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {

        return Objects.hash(creditCardId, cardNumber, cardOwner, cardType, validDate, contact);
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
