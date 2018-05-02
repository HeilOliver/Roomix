package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.domain.reservation.PaymentType;
import at.fhv.roomix.persist.dataaccess.factory.PaymentTypeFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.PaymentTypeEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.persist.builder
 * PaymentTypeBuilder
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PaymentTypeBuilder {
    private static final ModelMapper mapper = new ModelMapper();

    public static Collection<PaymentType> getPaymentTypes() throws BuilderLoadException {
        Collection<PaymentTypeEntity> all = null;
        try {
            all = PaymentTypeFactory.getInstance().getAll();
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        Collection<PaymentType> results = new HashSet<>();

        all.forEach((e) -> results.add(mapper.map(e, PaymentType.class)));
        return results;
    }

    public static PaymentType getPaymentType(int id) throws BuilderLoadException {
        PaymentTypeEntity entity = null;
        try {
            entity = PaymentTypeFactory.getInstance().get(id);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        if (entity == null) throw new BuilderLoadException(String.format("No PaymentType with the given id (%d) found", id));
        return mapper.map(entity, PaymentType.class);
    }
}
