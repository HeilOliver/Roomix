package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.InvoiceDomain;
import at.fhv.roomix.persist.InvoiceDao;
import at.fhv.roomix.persist.model.InvoiceEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;

public class InvoiceDomainBuilder extends AbstractDomainBuilder<InvoiceDomain, InvoiceEntity>
        implements IProxy<InvoiceDomain, Integer> {

    private InvoiceDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    InvoiceDomainBuilder(){}

    private static IProxy<InvoiceDomain, Integer> lazyInstance;

    public static IProxy<InvoiceDomain, Integer> getLazyInstance(){
        if(lazyInstance == null){
            lazyInstance = new InvoiceDomainBuilder();
        }
        return lazyInstance;
    }

    @Override
    protected InvoiceDomain mapEntityToDomain(InvoiceEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        InvoiceDomain invoiceDomain = modelMapper.map(entity, InvoiceDomain.class);
        /* TODO: Add proxies and collection mappings */
        return invoiceDomain;
    }

    @Override
    protected InvoiceEntity mapDomainToEntity(InvoiceDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        InvoiceEntity invoiceEntity = modelMapper.map(domain, InvoiceEntity.class);
        return invoiceEntity;
    }

    @Override
    public InvoiceDomain get(int id) {
        return new InvoiceDomainBuilder(InvoiceDao::registerAtDao).getById(id, InvoiceEntity.class);
    }

    @Override
    public List<InvoiceDomain> getAll() {
        return new InvoiceDomainBuilder(InvoiceDao::registerAtDao).loadAll(InvoiceEntity.class);
    }

    @Override
    public void set(InvoiceDomain domainObject) {
        new InvoiceDomainBuilder(InvoiceDao::registerAtDao).save(InvoiceEntity.class, domainObject);
    }

    @Override
    public InvoiceDomain lazyLoadInstance(Integer key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<InvoiceDomain> lazyLoadCollection(Integer key, String referencedColumn) {
        return new InvoiceDomainBuilder(InvoiceDao::registerAtDao).
                loadByForeignKey(InvoiceEntity.class, key, referencedColumn);
    }

}
