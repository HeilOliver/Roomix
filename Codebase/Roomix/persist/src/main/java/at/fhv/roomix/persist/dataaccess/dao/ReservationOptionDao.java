package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.domain.reservation.ReservationOption;
import at.fhv.roomix.persist.models.ReservationOptionEntity;

import javax.persistence.criteria.CriteriaBuilder;

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
