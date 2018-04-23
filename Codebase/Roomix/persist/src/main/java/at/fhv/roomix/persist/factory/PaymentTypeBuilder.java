package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.PaymentTypeDomain;
import at.fhv.roomix.persist.PaymentTypeDao;
import at.fhv.roomix.persist.model.PaymentTypeEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.function.Supplier;

public class PaymentTypeBuilder extends AbstractDomainBuilder<PaymentTypeDomain, PaymentTypeEntity>
        implements IAbstractDomainBuilder<PaymentTypeDomain, PaymentTypeEntity>{

    private PaymentTypeBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    PaymentTypeBuilder(){}

    private static Supplier<IAbstractDomainBuilder<PaymentTypeDomain, PaymentTypeEntity>> supplier;

    public static IAbstractDomainBuilder<PaymentTypeDomain, PaymentTypeEntity> getInstance() {
        if (supplier == null) return new PaymentTypeBuilder(PaymentTypeDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(
            Supplier<IAbstractDomainBuilder<PaymentTypeDomain, PaymentTypeEntity>> builderSupplier) {
        supplier = builderSupplier;
    }


    @Override
    protected PaymentTypeDomain mapEntityToDomain(PaymentTypeEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        PaymentTypeDomain paymentTypeDomain = modelMapper.map(entity, PaymentTypeDomain.class);
        return paymentTypeDomain;
    }

    @Override
    protected PaymentTypeEntity mapDomainToEntity(PaymentTypeDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        PaymentTypeEntity paymentTypeEntity = modelMapper.map(domain, PaymentTypeEntity.class);
        return paymentTypeEntity;
    }

    @Override
    public PaymentTypeDomain get(int id) {
        return new PaymentTypeBuilder(PaymentTypeDao::registerAtDao).getById(id, PaymentTypeEntity.class);
    }

    @Override
    public List<PaymentTypeDomain> getAll() {
        return null;
    }

    @Override
    public void set(PaymentTypeDomain domainObject) {

    }
}
