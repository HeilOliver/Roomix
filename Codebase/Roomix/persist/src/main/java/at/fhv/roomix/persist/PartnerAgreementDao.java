package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.PartnerAgreementEntity;

public class PartnerAgreementDao extends AbstractDao<PartnerAgreementEntity, Integer> {
    static {
        AbstractDao.addDao(PartnerAgreementEntity.class, PartnerAgreementDao::new);
    }

    private PartnerAgreementDao() {
        super(PartnerAgreementEntity.class);
    }


    public static PartnerAgreementDao getInstance() {
        return new PartnerAgreementDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Partner Agreement DAO");
    }
}

