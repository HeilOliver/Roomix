package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.domain.guest.model.ReservationDomain;
import at.fhv.roomix.persist.factory.IAbstractDomainBuilder;
import at.fhv.roomix.persist.model.ReservationEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation
 * GuestDomainBuilderMock
 * 08.04.2018 sge
 * <p>
 * Enter Description here
 */
public class ReservationDomainBuilderMock implements IAbstractDomainBuilder<ReservationDomain, ReservationEntity> {
    private boolean newReservationBool = false;
    private boolean getAllBool = false;

    @Override
    public ReservationDomain get(int id) {
        return new ReservationDomain();
    }

    @Override
    public List<ReservationDomain> getAll() {

        getAllBool = true;
        return new LinkedList<>();
    }

    @Override
    public void set(ReservationDomain domainObject) {

    }

    public boolean getNewReservation() {
        return newReservationBool;
    }

    public boolean isGetAllBool() {
        return getAllBool;
    }

    public void clearTest() {
        newReservationBool = false;
        getAllBool = false;
    }
}
