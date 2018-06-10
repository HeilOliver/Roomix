package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.reservation.Arrangement;
import at.fhv.roomix.domain.reservation.Person;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.room.Room;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.builder.accessbuilder.ArrangementBuilder;
import at.fhv.roomix.persist.builder.accessbuilder.GuestBuilder;
import at.fhv.roomix.persist.builder.accessbuilder.RoomCategoryBuilder;
import at.fhv.roomix.persist.dataaccess.factory.RoomCategoryFactory;
import at.fhv.roomix.persist.exception.BuilderException;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.persist.mappings
 * UnitMapping
 * 13/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class UnitMapping implements MapType<ReservationUnit, ReservationUnitEntity> {

    @Override
    public void map(ReservationUnit source, ReservationUnitEntity destination, Mapper mapper) throws MappingException {
        try {
            RoomCategoryEntity categoryEntity
                    = RoomCategoryFactory.getInstance().get(source.getCategory().getId());
            destination.setRoomCategory(categoryEntity);
        } catch (PersistLoadException e) {
            throw new MappingException("Mapping Error - " + e.getMessage(), e);
        }
        destination.setPrice(source.getPrice());
        destination.setArrivalTime(source.getArrivalTime());
        destination.setStartDate(source.getStartDate());
        destination.setEndDate(source.getEndDate());
        destination.setStatus(source.getStatus().toString());
    }

    @Override
    public void mapReverse(ReservationUnitEntity source, ReservationUnit destination, Mapper mapper) throws MappingException {
        destination.setStatus(ReservationUnit.UnitStatus.valueOf(source.getStatus()));
        destination.setCanceled(source.getCancellation() != null);
        destination.setPrice(source.getPrice());
        destination.setArrivalTime(source.getArrivalTime());
        destination.setStartDate(source.getStartDate());
        destination.setEndDate(source.getEndDate());
        destination.setId(source.getReservationUnitId());

        try {
            RoomCategory category = RoomCategoryBuilder.getRoomCategory(source.getRoomCategory().getRoomCategoryId());
            destination.setCategory(category);

            if (!source.getRoomAssignments().isEmpty()) {
                Collection<RoomAssignmentEntity> assignments = source.getRoomAssignments();
                for (RoomAssignmentEntity assignment : assignments) {
                    RoomEntity roomEntity = assignment.getRoom();
                    Room room = mapper.map(roomEntity, Room.class);
                    LocalDate currDate = assignment.getArrivalDate().toLocalDate();
                    LocalDate endDate = assignment.getDepartureDate().toLocalDate();
                    do {
                        destination.getAssignedRooms().put(currDate, room);
                        currDate = currDate.plusDays(1);
                    } while (currDate.isBefore(endDate));
                }
            }

            HashSet<Arrangement> arrangements = new HashSet<>();
            Collection<InvoicePositionEntity> invoicePositions = source.getInvoicePositions();
            for (InvoicePositionEntity position : invoicePositions) {
                if (position.getArticle() == null) continue;
                if (!position.getArticle().getArticleType().equals(ArticleEntity.ArticleType.ARRANGEMENT.toString())) continue;
                arrangements.add(ArrangementBuilder.getArrangement(position.getArticle().getArticleId()));
            }
            destination.setArrangements(arrangements);


            for (PersonEntity entity : source.getPersons()) {
                Person person = GuestBuilder.getPerson(entity.getPersonId());
                destination.getGuests().add(person);
            }

        } catch (BuilderLoadException e) {
            // TODO LOG Here
            e.printStackTrace();
        }
    }
}
