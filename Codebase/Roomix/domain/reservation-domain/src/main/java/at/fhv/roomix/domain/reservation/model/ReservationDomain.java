package at.fhv.roomix.domain.reservation.model;

import at.fhv.roomix.domain.guest.model.ContractingPartyDomain;
import at.fhv.roomix.domain.guest.model.PaymentTypeDomain;

import java.util.Collection;

public class ReservationDomain {

    private int reservationId;
    private int contractingParty;
    private int paymentType;
    private String reservationStatus;
    private String reservationComment;

    private Collection<PersonReservationDomain> personReservationsByReservationId;
    private Collection<ReservationUnitDomain> reservationUnitsByReservationId;

    private ContractingPartyDomain contractingPartyByContractingParty;
    private PaymentTypeDomain paymentTypeByPaymentType;
    private ReservationOptionDomain reservationOptionByReservationOption;

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

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getReservationComment() {
        return reservationComment;
    }

    public void setReservationComment(String reservationComment) {
        this.reservationComment = reservationComment;
    }

    public Collection<PersonReservationDomain> getPersonReservationsByReservationId() {
        return personReservationsByReservationId;
    }

    public void setPersonReservationsByReservationId(Collection<PersonReservationDomain> personReservationsByReservationId) {
        this.personReservationsByReservationId = personReservationsByReservationId;
    }

    public ContractingPartyDomain getContractingPartyByContractingParty() {
        return contractingPartyByContractingParty;
    }

    public void setContractingPartyByContractingParty(ContractingPartyDomain contractingPartyByContractingParty) {
        this.contractingPartyByContractingParty = contractingPartyByContractingParty;
    }

    public PaymentTypeDomain getPaymentTypeByPaymentType() {
        return paymentTypeByPaymentType;
    }

    public void setPaymentTypeByPaymentType(PaymentTypeDomain paymentTypeByPaymentType) {
        this.paymentTypeByPaymentType = paymentTypeByPaymentType;
    }

    public Collection<ReservationUnitDomain> getReservationUnitsByReservationId() {
        return reservationUnitsByReservationId;
    }

    public void setReservationUnitsByReservationId(Collection<ReservationUnitDomain> reservationUnitsByReservationId) {
        this.reservationUnitsByReservationId = reservationUnitsByReservationId;
    }


    public ReservationOptionDomain getReservationOptionByReservationOption() {
        return reservationOptionByReservationOption;
    }

    public void setReservationOptionByReservationOption(ReservationOptionDomain reservationOptionByReservationOption) {
        this.reservationOptionByReservationOption = reservationOptionByReservationOption;
    }
}