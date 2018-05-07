package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.ReservationEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * ReservationDao
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationDao extends AbstractDao<ReservationEntity, Integer> {

    public ReservationDao() {
        super(ReservationEntity.class);
    }

}
