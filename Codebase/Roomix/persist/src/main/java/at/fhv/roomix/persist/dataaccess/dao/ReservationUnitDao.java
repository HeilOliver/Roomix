package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.ReservationUnitEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * ReservationUnitDao
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationUnitDao extends AbstractDao<ReservationUnitEntity, Integer> {
    public ReservationUnitDao() {
        super(ReservationUnitEntity.class);
    }
}
