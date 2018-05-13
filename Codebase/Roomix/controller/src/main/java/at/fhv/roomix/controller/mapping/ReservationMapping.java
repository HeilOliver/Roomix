package at.fhv.roomix.controller.mapping;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.controller.model.*;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.reservation.*;

import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.controller.mapping
 * ReservationMapping
 * 12/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationMapping implements MapType<Reservation, ReservationPojo> {
    static {
        Mapper.getInstance().addMapType(new ContractingPartyMapping(), ContractingParty.class, ContactPojo.class);
        Mapper.getInstance().addMapType(new PaymentTypeMapping(), PaymentType.class, PaymentTypePojo.class);
        Mapper.getInstance().addMapType(new ReservationOptionMapping(), ReservationOption.class, ReservationOptionPojo.class);
        Mapper.getInstance().addMapType(new PersonMapping(), Person.class, PersonPojo.class);
        Mapper.getInstance().addMapType(new UnitMapping(), ReservationUnit.class, ReservationUnitPojo.class);
    }

    @Override
    public void map(Reservation source, ReservationPojo destination, Mapper mapper) throws MappingException {

        destination.setId(source.getId());

        // Add Comment if Exist
        if (source.getComment() != null) {
            destination.setReservationComment(source.getComment());
        }

        // Add Contracting Party
        ContactPojo contactPojo =
                mapper.map(source.getContractingParty(), ContactPojo.class);
        destination.setContractingParty(contactPojo);

        // Add Payment Type
        PaymentTypePojo typePojo = mapper.map(source.getPaymentType(), PaymentTypePojo.class);
        destination.setPaymentType(typePojo);

        // Add Reservation Option
        if (source.getOption() != null) {
            ReservationOptionPojo optionPojo = mapper.map(source.getOption(), ReservationOptionPojo.class);
            destination.setOption(optionPojo);
        }

        // Add Persons
        destination.setPersons(new HashSet<>());
        for (Person guest: source.getGuests()) {
            PersonPojo personPojo = mapper.map(guest, PersonPojo.class);
            destination.getPersons().add(personPojo);
        }

        // ForEach Unit
        destination.setUnits(new HashSet<>());
        for (ReservationUnit unit : source.getUnits()) {
            destination.getUnits().add(mapper.map(unit,ReservationUnitPojo.class));
        }
    }
}
