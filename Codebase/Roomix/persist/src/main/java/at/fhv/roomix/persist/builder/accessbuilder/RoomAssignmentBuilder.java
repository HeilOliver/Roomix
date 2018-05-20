package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.domain.reservation.RoomAssignment;
import at.fhv.roomix.persist.dataaccess.factory.RoomAssignmentFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.mappings.RoomAssignmentMapping;
import at.fhv.roomix.persist.models.RoomAssignmentEntity;

import javax.print.attribute.standard.Finishings;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.persist.builder.accessbuilder
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class RoomAssignmentBuilder {
    private final static Mapper mapper;

    static {
        mapper = Mapper.getInstance();
        mapper.addMapType(new RoomAssignmentMapping(), RoomAssignmentEntity.class, RoomAssignment.class);
    }


    public static void save(RoomAssignment assignment) throws BuilderUpdateException {
        RoomAssignmentEntity entity;
        RoomAssignmentFactory factory = RoomAssignmentFactory.getInstance();
        try {
            entity = assignment.getId() == 0 ? new RoomAssignmentEntity() : factory.get(assignment.getId());
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("", e);
        }

        mapper.map(assignment, entity);
        factory.saveOrUpdate(entity);
    }

    public static Collection<RoomAssignment> getAll() throws BuilderLoadException {
        RoomAssignmentFactory factory = RoomAssignmentFactory.getInstance();
        List<RoomAssignmentEntity> entities;
        try {
            entities = factory.getAll();
        } catch (PersistLoadException e) {
            throw new BuilderLoadException("", e);
        }

        return entities.stream().map(a -> mapper.map(a, RoomAssignment.class)).collect(Collectors.toSet());
    }

    public static void delete(RoomAssignment assignment) throws BuilderUpdateException {
        RoomAssignmentEntity entity;
        RoomAssignmentFactory factory = RoomAssignmentFactory.getInstance();
        try {
            entity = factory.get(assignment.getId());
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("", e);
        }

        factory.delete(entity);
    }
}
