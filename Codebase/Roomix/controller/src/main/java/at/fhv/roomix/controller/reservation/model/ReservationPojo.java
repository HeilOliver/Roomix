package at.fhv.roomix.controller.reservation.model;

import at.fhv.roomix.controller.contact.model.ContactPojo;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.contact
 * ReservationPojo
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationPojo {

    private int id;

    private CommentPojo comment;
    @NotNull(message = "contractingParty cannot be null")
    private ContactPojo contractingParty;
    @NotNull(message = "reservationUnitsByReservationId cannot be null")
    private Collection<ReservationUnitPojo> reservationUnitsByReservationId;

    /* TODO: not a collection. only one reservation option per reservation */
    private Collection<ReservationOptionPojo> reservationOptionByReservationOption;
    @NotNull(message = "personReservationsByReservationId cannot be null")
    private Collection<ContactPojo> personReservationsByReservationId;
    private String reservationStatus;
    private int paymentType;

    public ReservationPojo() {
        reservationUnitsByReservationId = new HashSet<>();
        personReservationsByReservationId = new HashSet<>();
        reservationOptionByReservationOption = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactPojo getContractingParty() {
        return contractingParty;
    }

    public void setContractingParty(ContactPojo contractingParty) {
        this.contractingParty = contractingParty;
    }

    public Collection<ReservationUnitPojo> getReservationUnitsByReservationId() {
        return reservationUnitsByReservationId;
    }

    public void setReservationUnitsByReservationId(Collection<ReservationUnitPojo> reservationUnitsByReservationId) {
        this.reservationUnitsByReservationId = reservationUnitsByReservationId;
    }

    public Collection<ContactPojo> getPersonReservationsByReservationId() {
        return personReservationsByReservationId;
    }

    public void setPersonReservationsByReservationId(Collection<ContactPojo> personReservationsByReservationId) {
        this.personReservationsByReservationId = personReservationsByReservationId;
    }

    public CommentPojo getComment() {
        return comment;
    }

    public void setComment(CommentPojo comment) {
        this.comment = comment;
    }

    public Collection<ReservationOptionPojo> getReservationOptionByReservationOption() {
        return reservationOptionByReservationOption;
    }

    public void setReservationOptionByReservationOption(Collection<ReservationOptionPojo> reservationOptionByReservationOption) {
        this.reservationOptionByReservationOption = reservationOptionByReservationOption;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
}
