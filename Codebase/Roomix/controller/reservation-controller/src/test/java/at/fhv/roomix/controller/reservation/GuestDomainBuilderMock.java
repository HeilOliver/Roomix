package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.domain.guest.model.GuestDomain;
import at.fhv.roomix.persist.factory.IAbstractDomainBuilder;
import at.fhv.roomix.persist.model.ContactEntity;

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
public class GuestDomainBuilderMock implements IAbstractDomainBuilder<GuestDomain, ContactEntity> {
    private boolean newContactBool = false;
    private boolean getAllBool = false;

    @Override
    public GuestDomain get(int id) {
        return new GuestDomain();
    }

    @Override
    public List<GuestDomain> getAll() {

        getAllBool = true;
        return new LinkedList<>();
    }

    @Override
    public void set(GuestDomain domainObject) {
        newContactBool = true;
    }

    public boolean getNewContactBool() {
        return newContactBool;
    }

    public boolean isGetAllBool() {
        return getAllBool;
    }

    public void clearTest() {
        newContactBool = false;
        getAllBool = false;
    }
}
