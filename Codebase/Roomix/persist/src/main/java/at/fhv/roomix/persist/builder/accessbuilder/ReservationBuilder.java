package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.domain.reservation.Reservation;
import at.fhv.roomix.persist.dataaccess.factory.PaymentTypeFactory;
import at.fhv.roomix.persist.dataaccess.factory.ReservationFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import sun.util.resources.cldr.mas.CalendarData_mas_KE;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.persist.builder
 * ReservationBuilder
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationBuilder {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Reservation, ReservationEntity>() {
            @Override
            protected void configure() {
                skip().setContractingParty(null);
                skip().setInvoicePositions(null);
                skip().setUnits(null);
                skip().setPaymentType(null);
                skip().setGuests(null);
                skip().setOption(null);
            }
        });
        mapper.addMappings(new PropertyMap<ReservationEntity, Reservation>() {
            @Override
            protected void configure() {
                skip().setContractingParty(null);
                skip().setUnits(null);
                skip().setPaymentType(null);
                skip().setGuests(null);
                skip().setOption(null);
            }
        });
    }

    private static Reservation fromEntity(ReservationEntity entity) throws BuilderLoadException {
        try {
            Reservation reservation = mapper.map(entity, Reservation.class);
            if (entity.getOption() != null)
                reservation.setOption(
                        ReservationOptionBuilder.get(entity.getOption().getOptionId()));
            reservation.setPaymentType(
                    PaymentTypeBuilder.getPaymentType(entity.getPaymentType().getPaymentTypeId()));
            reservation.setContractingParty(
                    ContractingPartyBuilder.get(entity.getContractingParty().getContractingPartyId()));
            reservation.setUnits(
                    entity.getUnits()
                            .stream()
                            .map((e) -> ReservationUnitBuilder
                                    .getUC(e.getReservationUnitId()))
                            .collect(Collectors.toSet()));

            reservation.setGuests(entity.getGuests()
                    .stream()
                    .map((e) -> GuestBuilder.getPersonUC(e.getPersonId()))
                    .collect(Collectors.toSet()));
            return reservation;
        } catch (RuntimeException e) {
            throw new BuilderLoadException(e.getMessage(), e.getCause());
        }
    }

    public static void update(Reservation obj) throws BuilderUpdateException {
        ReservationEntity entity;
        try {
            entity = obj.getId() == 0 ? new ReservationEntity() : ReservationFactory.getInstance().get(obj.getId());
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("", e);
        }
        ContractingPartyEntity party = ContractingPartyBuilder.updateParty(obj.getContractingParty());
        entity.setContractingParty(party);

        try {
            List<ReservationUnitEntity> collect =
                    obj.getUnits().stream()
                            .map(ReservationUnitBuilder::updateUnitUC)
                            .collect(Collectors.toList());
            entity.getUnits().clear();
            entity.getUnits().addAll(collect);
            collect.forEach((u) -> u.setReservation(entity));
        } catch (RuntimeException e) {
            throw new BuilderUpdateException(e.getCause());
        }

        try {
            Set<PersonEntity> persons = obj.getGuests().stream()
                    .map(GuestBuilder::updateGuestsUC)
                    .collect(Collectors.toSet());
            entity.getGuests().clear();
            entity.getGuests().addAll(persons);
            persons.forEach((p) -> p.getReservations().add(entity));
        } catch (RuntimeException e) {
            throw new BuilderUpdateException(e.getCause());
        }

        try {
            PaymentTypeEntity typeEntity = PaymentTypeFactory.getInstance().get(obj.getPaymentType().getId());
            entity.setPaymentType(typeEntity);
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("", e);
        }


        ReservationOptionEntity optionEntity = ReservationOptionBuilder.updateOption(obj.getOption());
        entity.setOption(optionEntity);

        mapper.map(obj, entity);

        ReservationFactory.getInstance().saveOrUpdate(entity);
    }

    public static Collection<Reservation> getAll() throws BuilderLoadException {

        List<ReservationEntity> all = null;
        try {
            all = ReservationFactory.getInstance().getAll();
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }

        HashSet<Reservation> result = new HashSet<>();
        for (ReservationEntity entity : all) {
            result.add(fromEntity(entity));
        }

        return result;
    }

    public static Reservation get(int reservationId) throws BuilderLoadException {
        ReservationEntity entity = null;
        try {
            entity = ReservationFactory.getInstance().get(reservationId);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        return fromEntity(entity);
    }
}
