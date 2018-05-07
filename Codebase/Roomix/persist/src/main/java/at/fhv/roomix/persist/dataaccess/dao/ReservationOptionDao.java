package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.ReservationOptionEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * ReservationOptionDao
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationOptionDao extends AbstractDao<ReservationOptionEntity, Integer> {

    public ReservationOptionDao() {
        super(ReservationOptionEntity.class);
    }
}
