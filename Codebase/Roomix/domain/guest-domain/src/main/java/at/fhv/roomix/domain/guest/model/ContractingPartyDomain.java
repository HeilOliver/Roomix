package at.fhv.roomix.domain.guest.model;

import java.util.Collection;

public class ContractingPartyDomain {
    private int contractingPartyId;
    private String contractingPartyType;
    private int contact;

    private GuestDomain contactByContact;
    /* Mapped by proxy */
    private Collection<PartnerAgreementDomain> partnerAgreementsByContractingPartyId;
    private Collection<ReservationDomain> reservationsByContractingPartyId;

    private Proxy<Collection<PartnerAgreementDomain>, Integer> partnerAgreementProxy;
    private Proxy<Collection<ReservationDomain>, Integer> reservationProxy;

    public void setPartnerAgreementProxy(Proxy<Collection<PartnerAgreementDomain>, Integer> partnerAgreementProxy) {
        this.partnerAgreementProxy = partnerAgreementProxy;
    }

    public void setReservationProxy(Proxy<Collection<ReservationDomain>, Integer> reservationProxy) {
        this.reservationProxy = reservationProxy;
    }

    public int getContractingPartyId() {
        return contractingPartyId;
    }

    public void setContractingPartyId(int contractingPartyId) {
        this.contractingPartyId = contractingPartyId;
    }

    public String getContractingPartyType() {
        return contractingPartyType;
    }

    public void setContractingPartyType(String contractingPartyType) {
        this.contractingPartyType = contractingPartyType;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public GuestDomain getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(GuestDomain contactByContact) {
        this.contactByContact = contactByContact;
    }

    public Collection<PartnerAgreementDomain> getPartnerAgreementsByContractingPartyId() {
        if (partnerAgreementProxy != null) {
            return (partnerAgreementsByContractingPartyId = partnerAgreementProxy.get());
        } else{
            return partnerAgreementsByContractingPartyId;
        }
    }

    public void setPartnerAgreementsByContractingPartyId(Collection<PartnerAgreementDomain> partnerAgreementsByContractingPartyId) {
        this.partnerAgreementsByContractingPartyId = partnerAgreementsByContractingPartyId;
    }

    public Collection<ReservationDomain> getReservationsByContractingPartyId() {
        if(reservationProxy != null) {
            return (reservationsByContractingPartyId = reservationProxy.get());
        } else {
            return reservationsByContractingPartyId;
        }
    }

    public void setReservationsByContractingPartyId(Collection<ReservationDomain> reservationsByContractingPartyId) {
        this.reservationsByContractingPartyId = reservationsByContractingPartyId;
    }
}
