package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.PaymentTypeEntity;

public class PaymentTypeDao extends AbstractDao<PaymentTypeEntity, Integer> {
    static {
        AbstractDao.addDao(PaymentTypeEntity.class, PaymentTypeDao::new);
    }

    private PaymentTypeDao() {
        super(PaymentTypeEntity.class);
    }


    public static PaymentTypeDao getInstance() {
        return new PaymentTypeDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at PaymentType DAO");
    }
}
