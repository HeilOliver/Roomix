package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Person", schema = "Roomix", catalog = "")
public class PersonEntity {
    private int personId;
    private byte isVip;
    private byte archive;
    private ContactEntity contactByContact;

    @Id
    @Column(name = "PersonID")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "IsVIP")
    public byte getIsVip() {
        return isVip;
    }

    public void setIsVip(byte isVip) {
        this.isVip = isVip;
    }

    @Basic
    @Column(name = "Archive")
    public byte getArchive() {
        return archive;
    }

    public void setArchive(byte archive) {
        this.archive = archive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return personId == that.personId &&
                isVip == that.isVip &&
                archive == that.archive;
    }

    @Override
    public int hashCode() {

        return Objects.hash(personId, isVip, archive);
    }

    @ManyToOne
    @JoinColumn(name = "Contact", referencedColumnName = "ContactID", nullable = false)
    public ContactEntity getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(ContactEntity contactByContact) {
        this.contactByContact = contactByContact;
    }
}
