package at.fhv.roomix.domain.guest.model;

import java.util.Collection;

public class ReservationDomain {
    private int reservationId;
    private int contractingParty;
    private int person;
    private String reservationStatus;

    private Collection<InvoicePositionDomain> invoicePositions;
    private Collection<ReservationOptionDomain> reservationOptions;
    private Collection<ReservationUnitDomain> reservationUnits;

    private PersonDomain referencedPerson;
    private ContractingPartyDomain referencedContractingParty;

    public PersonDomain getReferencedPerson() {
        return referencedPerson;
    }

    public void setReferencedPerson(PersonDomain referencedPerson) {
        this.referencedPerson = referencedPerson;
    }

    public ContractingPartyDomain getReferencedContractingParty() {
        return referencedContractingParty;
    }

    public void setReferencedContractingParty(ContractingPartyDomain referencedContractingParty) {
        this.referencedContractingParty = referencedContractingParty;
    }

    public Collection<InvoicePositionDomain> getInvoicePositions() {
        return invoicePositions;
    }

    public void setInvoicePositions(Collection<InvoicePositionDomain> invoicePositions) {
        this.invoicePositions = invoicePositions;
    }

    public Collection<ReservationOptionDomain> getReservationOptions() {
        return reservationOptions;
    }

    public void setReservationOptions(Collection<ReservationOptionDomain> reservationOptions) {
        this.reservationOptions = reservationOptions;
    }

    public Collection<ReservationUnitDomain> getReservationUnits() {
        return reservationUnits;
    }

    public void setReservationUnits(Collection<ReservationUnitDomain> reservationUnits) {
        this.reservationUnits = reservationUnits;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(int contractingParty) {
        this.contractingParty = contractingParty;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
