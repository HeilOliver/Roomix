package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.GuestDomain;
import at.fhv.roomix.domain.guest.model.ReservationDomain;
import at.fhv.roomix.persist.ReservationDao;
import at.fhv.roomix.persist.model.ReservationEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ReservationDomainBuilder extends AbstractDomainBuilder<ReservationDomain, ReservationEntity> {

    private ReservationDomainBuilder(ICallable registerAtDAO){
        registerAtDAO.call();
    }

    public ReservationDomainBuilder(){

    }


    @Override
    protected ReservationDomain mapEntityToDomain(ReservationEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        ReservationDomain reservationDomain = modelMapper.map(entity, ReservationDomain.class);
        return reservationDomain;
    }

    @Override
    protected ReservationEntity mapDomainToEntity(ReservationDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        ReservationEntity reservationEntity = modelMapper.map(domain, ReservationEntity.class);
        return reservationEntity;
    }

    @Override
    public ReservationDomain get(int id) {
        return new ReservationDomain(ReservationDao::);
    }

    @Override
    public List<ReservationDomain> getAll() {
        return null;
    }

    @Override
    public void set(ReservationDomain domainObject) {

    }
}
