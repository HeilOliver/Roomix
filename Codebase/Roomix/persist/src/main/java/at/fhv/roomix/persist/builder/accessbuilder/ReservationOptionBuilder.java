package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.domain.reservation.ReservationOption;
import at.fhv.roomix.persist.dataaccess.factory.ReservationOptionFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.ReservationOptionEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * Roomix
 * at.fhv.roomix.persist.builder
 * ReservationOptionBuilder
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationOptionBuilder {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<ReservationOption, ReservationOptionEntity>() {
            @Override
            protected void configure() {
                skip().setReservations(null);
            }
        });
    }

    static ReservationOption get(int id) throws BuilderLoadException {
        ReservationOptionEntity entity;
        try {
            entity = ReservationOptionFactory.getInstance().get(id);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        return mapper.map(entity, ReservationOption.class);
    }

    static ReservationOptionEntity updateOption(ReservationOption option) throws BuilderUpdateException {
        if (option == null) return null;
        ReservationOptionEntity entity;
        try {
            entity = option.getId() == 0 ? new ReservationOptionEntity() : ReservationOptionFactory.getInstance().get(option.getId());
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("", e);
        }

        mapper.map(option, entity);

        ReservationOptionFactory.getInstance().saveOrUpdate(entity);
        return entity;
    }
}
