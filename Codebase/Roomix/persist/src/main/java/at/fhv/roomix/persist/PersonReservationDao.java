package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.PersonReservationEntity;

public class PersonReservationDao extends AbstractDao<PersonReservationEntity, Integer>{
    static {
        AbstractDao.addDao(PersonReservationEntity.class, PersonReservationDao::new);
    }

    private PersonReservationDao() {
        super(PersonReservationEntity.class);
    }


    public static PersonReservationDao getInstance() {
        return new PersonReservationDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Person Reservation DAO");
    }
}
