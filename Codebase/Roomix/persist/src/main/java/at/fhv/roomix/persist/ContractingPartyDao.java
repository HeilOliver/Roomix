package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.ContractingPartyEntity;
import org.hibernate.HibernateException;

public class ContractingPartyDao extends AbstractDao<ContractingPartyEntity, Integer> {

    ContractingPartyDao() {
        super(ContractingPartyEntity.class);
    }

    public ContractingPartyDao getInstance() {
        return new ContractingPartyDao();
    }

    @Override
    protected void internalSave(ContractingPartyEntity entity) throws HibernateException {
        session.beginTransaction();
        session.saveOrUpdate(entity.getContactByContact());
        session.saveOrUpdate(entity.getPartnerAgreementsByContractingPartyId());
        session.saveOrUpdate(entity.getReservationsByContractingPartyId());
        session.save(entity);
        session.getTransaction().commit();
    }
}
