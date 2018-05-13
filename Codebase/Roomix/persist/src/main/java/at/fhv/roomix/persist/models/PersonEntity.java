package at.fhv.roomix.persist.models;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Person", schema = "Roomix", catalog = "")
public class PersonEntity {
    private int personId;
    private byte isVip;
    private byte archive;
    private String firstName;
    private String lastName;

    private ContactEntity contact;

    private Collection<ReservationEntity> reservations = new HashSet<>();
    private Collection<RoomAssignmentEntity> roomAssignments = new HashSet<>();
    private Collection<TourGroupEntity> tourGroupsByPersonId = new HashSet<>();
    private Collection<TourGroupMemberEntity> tourGroupMembersByPersonId = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "Contact")
    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contactEntity) {
        this.contact = contactEntity;
    }

    @ManyToMany()
    @JoinTable(name = "PersonReservation", joinColumns = {@JoinColumn(name = "Person")},
            inverseJoinColumns = {@JoinColumn(name = "Reservation")})
    public Collection<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<ReservationEntity> personReservationsByPersonId) {
        this.reservations = personReservationsByPersonId;
    }


    private Collection<ReservationUnitEntity> guestsAtUnit = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "PersonRoomAssignment", joinColumns = {@JoinColumn(name = "Person")},
            inverseJoinColumns = {@JoinColumn(name = "Unit")})
    public Collection<ReservationUnitEntity> getGuestsAtUnit() {
        return guestsAtUnit;
    }

    public void setGuestsAtUnit(Collection<ReservationUnitEntity> guestsAtUnit) {
        this.guestsAtUnit = guestsAtUnit;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "personByTourGroupLeader")
    public Collection<TourGroupEntity> getTourGroupsByPersonId() {
        return tourGroupsByPersonId;
    }

    public void setTourGroupsByPersonId(Collection<TourGroupEntity> tourGroupsByPersonId) {
        this.tourGroupsByPersonId = tourGroupsByPersonId;
    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "personByTourGroupMember")
    public Collection<TourGroupMemberEntity> getTourGroupMembersByPersonId() {
        return tourGroupMembersByPersonId;
    }

    public void setTourGroupMembersByPersonId(Collection<TourGroupMemberEntity> tourGroupMembersByPersonId) {
        this.tourGroupMembersByPersonId = tourGroupMembersByPersonId;
    }
}
