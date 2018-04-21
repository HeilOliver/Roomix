package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.IProxy;
import at.fhv.roomix.domain.guest.model.OccupationStatusHelper;
import at.fhv.roomix.domain.guest.model.RoomCategoryDomain;
import at.fhv.roomix.persist.RoomCategoryDao;
import at.fhv.roomix.persist.model.RoomCategoryEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class RoomCategoryDomainBuilder extends AbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity>
            implements IAbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity>, IProxy<RoomCategoryDomain, RoomCategoryEntity> {

    private RoomCategoryDomainBuilder(ICallable registerAtDAO) {
        registerAtDAO.call();
    }

    RoomCategoryDomainBuilder(){}

    private static Supplier<IAbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity>> supplier;

    public static IAbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity> getInstance() {
        if (supplier == null) return new RoomCategoryDomainBuilder(RoomCategoryDao::registerAtDao);
        return supplier.get();
    }

    public static void injectDependency(Supplier<IAbstractDomainBuilder<RoomCategoryDomain, RoomCategoryEntity>> builderSupplier) {
        supplier = builderSupplier;
    }

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


    @Override
    public RoomCategoryDomain lazyLoadInstance(RoomCategoryEntity key, String referencedColumn) {
        return null;
    }

    @Override
    public Collection<RoomCategoryDomain> lazyLoadCollection(RoomCategoryEntity key, String referencedColumn) {
        return null;
    }
}
