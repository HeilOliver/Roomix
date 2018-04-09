package at.fhv.roomix.persist;

import at.fhv.roomix.persist.model.ContractingpartyEntity;
import org.hibernate.HibernateException;

public class ContractingPartyDao extends AbstractDao<ContractingpartyEntity, Integer> {

    ContractingPartyDao() {
        super(ContractingpartyEntity.class);
    }

    public ContractingPartyDao getInstance() {
        return new ContractingPartyDao();
    }

    @Override
    protected void internalSave(ContractingpartyEntity entity) throws HibernateException {
        session.beginTransaction();
        session.saveOrUpdate(entity.getContactByContact());
        session.saveOrUpdate(entity.getPartneragreementsByContractingPartyId());
        session.saveOrUpdate(entity.getReservationsByContractingPartyId());
        session.save(entity);
        session.getTransaction().commit();
    }
}
