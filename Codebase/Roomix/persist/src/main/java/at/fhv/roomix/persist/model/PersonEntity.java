package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Person", schema = "roomix", catalog = "")
public class PersonEntity {
    private int personId;
    private byte isVip;
    private byte archive;
    private int contact;
    private ContactEntity contactByContact;
    private Collection<PersonroomassignmentEntity> personroomassignmentsByPersonId;
    private Collection<ReservationEntity> reservationsByPersonId;
    private Collection<TourgroupEntity> tourgroupsByPersonId;
    private Collection<TourgroupmemberEntity> tourgroupmembersByPersonId;

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

    @Basic
    @Column(name = "Contact", insertable=false, updatable=false)
    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return personId == that.personId &&
                isVip == that.isVip &&
                archive == that.archive &&
                contact == that.contact;
    }

    @Override
    public int hashCode() {

        return Objects.hash(personId, isVip, archive, contact);
    }

    @ManyToOne
    @JoinColumn(name = "Contact", referencedColumnName = "ContactID", nullable = false)
    public ContactEntity getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(ContactEntity contactByContact) {
        this.contactByContact = contactByContact;
    }

    @OneToMany(mappedBy = "personByPerson")
    public Collection<PersonroomassignmentEntity> getPersonroomassignmentsByPersonId() {
        return personroomassignmentsByPersonId;
    }

    public void setPersonroomassignmentsByPersonId(Collection<PersonroomassignmentEntity> personroomassignmentsByPersonId) {
        this.personroomassignmentsByPersonId = personroomassignmentsByPersonId;
    }

    @OneToMany(mappedBy = "personByPerson")
    public Collection<ReservationEntity> getReservationsByPersonId() {
        return reservationsByPersonId;
    }

    public void setReservationsByPersonId(Collection<ReservationEntity> reservationsByPersonId) {
        this.reservationsByPersonId = reservationsByPersonId;
    }

    @OneToMany(mappedBy = "personByTourGroupLeader")
    public Collection<TourgroupEntity> getTourgroupsByPersonId() {
        return tourgroupsByPersonId;
    }

    public void setTourgroupsByPersonId(Collection<TourgroupEntity> tourgroupsByPersonId) {
        this.tourgroupsByPersonId = tourgroupsByPersonId;
    }

    @OneToMany(mappedBy = "personByTourGroupMember")
    public Collection<TourgroupmemberEntity> getTourgroupmembersByPersonId() {
        return tourgroupmembersByPersonId;
    }

    public void setTourgroupmembersByPersonId(Collection<TourgroupmemberEntity> tourgroupmembersByPersonId) {
        this.tourgroupmembersByPersonId = tourgroupmembersByPersonId;
    }
}
