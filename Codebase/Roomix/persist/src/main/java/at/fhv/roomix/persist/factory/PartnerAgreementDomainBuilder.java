package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.PartnerAgreementDomain;
import at.fhv.roomix.persist.model.PartnerAgreementEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;

public class PartnerAgreementDomainBuilder extends AbstractDomainBuilder<PartnerAgreementDomain, PartnerAgreementEntity>
            implements IProxy<PartnerAgreementDomain, Integer> {

    private PartnerAgreementDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    PartnerAgreementDomainBuilder(){}

    private static IProxy<PartnerAgreementDomain, Integer> lazyInstance;

    public static IProxy<PartnerAgreementDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new PartnerAgreementDomainBuilder();
        }
        return lazyInstance;
    }

    @Override
    protected PartnerAgreementDomain mapEntityToDomain(PartnerAgreementEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        PartnerAgreementDomain partnerAgreementDomain = modelMapper.map(entity, PartnerAgreementDomain.class);
        return partnerAgreementDomain;
    }

    @Override
    protected PartnerAgreementEntity mapDomainToEntity(PartnerAgreementDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        PartnerAgreementEntity partnerAgreementEntity = modelMapper.map(domain, PartnerAgreementEntity.class);
        return partnerAgreementEntity;
    }

    @Override
    public PartnerAgreementDomain get(int id) {
        return null;
    }

    @Override
    public List<PartnerAgreementDomain> getAll() {
        return null;
    }

    @Override
    public void set(PartnerAgreementDomain domainObject) {

    }

    @Override
    public PartnerAgreementDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<PartnerAgreementDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return null;
    }
}
