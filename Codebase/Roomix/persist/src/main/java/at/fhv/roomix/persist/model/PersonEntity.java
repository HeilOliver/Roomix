package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Person", schema = "Roomix", catalog = "")
public class PersonEntity {
    private int personId;
    private byte isVip;
    private byte archive;
    private int contact;
    private String firstName;
    private String lastName;
    private ContactEntity contactByContact;
    private Collection<PersonReservationEntity> personReservationsByPersonId;
    private Collection<PersonRoomAssignmentEntity> personRoomAssignmentsByPersonId;
    private Collection<TourGroupEntity> tourGroupsByPersonId;
    private Collection<TourGroupMemberEntity> tourGroupMembersByPersonId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PersonID")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    @Column(name = "Contact", insertable = false, updatable = false)
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
    @JoinColumn(name = "Contact", referencedColumnName = "ContactID", insertable = false, updatable = false)
    public ContactEntity getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(ContactEntity contactByContact) {
        this.contactByContact = contactByContact;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "personByPerson")
    public Collection<PersonReservationEntity> getPersonReservationsByPersonId() {
        return personReservationsByPersonId;
    }

    public void setPersonReservationsByPersonId(Collection<PersonReservationEntity> personReservationsByPersonId) {
        this.personReservationsByPersonId = personReservationsByPersonId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "personByPerson")
    public Collection<PersonRoomAssignmentEntity> getPersonRoomAssignmentsByPersonId() {
        return personRoomAssignmentsByPersonId;
    }

    public void setPersonRoomAssignmentsByPersonId(Collection<PersonRoomAssignmentEntity> personRoomAssignmentsByPersonId) {
        this.personRoomAssignmentsByPersonId = personRoomAssignmentsByPersonId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "personByTourGroupLeader")
    public Collection<TourGroupEntity> getTourGroupsByPersonId() {
        return tourGroupsByPersonId;
    }

    public void setTourGroupsByPersonId(Collection<TourGroupEntity> tourGroupsByPersonId) {
        this.tourGroupsByPersonId = tourGroupsByPersonId;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "personByTourGroupMember")
    public Collection<TourGroupMemberEntity> getTourGroupMembersByPersonId() {
        return tourGroupMembersByPersonId;
    }

    public void setTourGroupMembersByPersonId(Collection<TourGroupMemberEntity> tourGroupMembersByPersonId) {
        this.tourGroupMembersByPersonId = tourGroupMembersByPersonId;
    }
}
