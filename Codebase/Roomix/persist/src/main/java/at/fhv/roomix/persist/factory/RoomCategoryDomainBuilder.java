package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.reservation.helper.OccupationStatusHelper;
import at.fhv.roomix.domain.reservation.model.RoomCategoryDomain;
import at.fhv.roomix.persist.model.RoomCategoryEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

public class RoomCategoryDomainBuilder extends AbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity> {

    @Override
    protected RoomCategoryDomain mapEntityToDomain(RoomCategoryEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        RoomCategoryDomain roomCategoryDomain = modelMapper.map(entity, RoomCategoryDomain.class);
        return roomCategoryDomain;
    }

    @Override
    protected RoomCategoryEntity mapDomainToEntity(RoomCategoryDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        RoomCategoryEntity roomCategoryEntity = modelMapper.map(domain, RoomCategoryEntity.class);
        return roomCategoryEntity;
    }

    @Override
    public RoomCategoryDomain get(int id) {
        return null;
    }

    @Override
    public List<RoomCategoryDomain> getAll() {
        return null;
    }

    @Override
    public void set(RoomCategoryDomain domainObject) {

    }

    private OccupationStatusHelper getOccupationStatus(){
        return null;
    }

    public void getOccupationStatusFromDatabase(){

        List<RoomCategoryEntity> roomCategoryRoot = loadAllEntites(RoomCategoryEntity.class);
        roomCategoryRoot.forEach(roomCategoryEntity -> {

        });
    }


}
