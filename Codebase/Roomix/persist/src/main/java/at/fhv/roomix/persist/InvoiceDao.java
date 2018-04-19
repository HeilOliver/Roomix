package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.InvoiceEntity;

public class InvoiceDao extends AbstractDao<InvoiceEntity, Integer> {


    static {
        AbstractDao.addDao(InvoiceEntity.class, InvoiceDao::new);
    }

    private InvoiceDao() {
        super(InvoiceEntity.class);
    }

    public static InvoiceDao getInstance() {
        return new InvoiceDao();
    }

    public static void registerAtDao() {
        daoLogger.info("Registered at Invoice DAO");
    }
}
