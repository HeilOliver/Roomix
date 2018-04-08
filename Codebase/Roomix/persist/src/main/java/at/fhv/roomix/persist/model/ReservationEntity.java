package at.fhv.roomix.persist.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Reservation", schema = "roomix", catalog = "")
public class ReservationEntity {
    private int reservationId;
    private int contractingParty;
    private int person;
    private String reservationStatus;
    private Collection<InvoicepositionEntity> invoicepositionsByReservationId;
    private ContractingpartyEntity contractingpartyByContractingParty;
    private PersonEntity personByPerson;
    private Collection<ReservationoptionEntity> reservationoptionsByReservationId;
    private Collection<ReservationunitEntity> reservationunitsByReservationId;

    @Id
    @Column(name = "ReservationID")
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Basic
    @Column(name = "ContractingParty", insertable=false, updatable=false)
    public int getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(int contractingParty) {
        this.contractingParty = contractingParty;
    }

    @Basic
    @Column(name = "Person", insertable=false, updatable=false)
    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    @Basic
    @Column(name = "ReservationStatus")
    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntity that = (ReservationEntity) o;
        return reservationId == that.reservationId &&
                contractingParty == that.contractingParty &&
                person == that.person &&
                Objects.equals(reservationStatus, that.reservationStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reservationId, contractingParty, person, reservationStatus);
    }

    @OneToMany(mappedBy = "reservationByReservation")
    public Collection<InvoicepositionEntity> getInvoicepositionsByReservationId() {
        return invoicepositionsByReservationId;
    }

    public void setInvoicepositionsByReservationId(Collection<InvoicepositionEntity> invoicepositionsByReservationId) {
        this.invoicepositionsByReservationId = invoicepositionsByReservationId;
    }

    @ManyToOne
    @JoinColumn(name = "ContractingParty", referencedColumnName = "ContractingPartyID", nullable = false)
    public ContractingpartyEntity getContractingpartyByContractingParty() {
        return contractingpartyByContractingParty;
    }

    public void setContractingpartyByContractingParty(ContractingpartyEntity contractingpartyByContractingParty) {
        this.contractingpartyByContractingParty = contractingpartyByContractingParty;
    }

    @ManyToOne
    @JoinColumn(name = "Person", referencedColumnName = "PersonID", nullable = false)
    public PersonEntity getPersonByPerson() {
        return personByPerson;
    }

    public void setPersonByPerson(PersonEntity personByPerson) {
        this.personByPerson = personByPerson;
    }

    @OneToMany(mappedBy = "reservationByReservation")
    public Collection<ReservationoptionEntity> getReservationoptionsByReservationId() {
        return reservationoptionsByReservationId;
    }

    public void setReservationoptionsByReservationId(Collection<ReservationoptionEntity> reservationoptionsByReservationId) {
        this.reservationoptionsByReservationId = reservationoptionsByReservationId;
    }

    @OneToMany(mappedBy = "reservationByReservation")
    public Collection<ReservationunitEntity> getReservationunitsByReservationId() {
        return reservationunitsByReservationId;
    }

    public void setReservationunitsByReservationId(Collection<ReservationunitEntity> reservationunitsByReservationId) {
        this.reservationunitsByReservationId = reservationunitsByReservationId;
    }
}
