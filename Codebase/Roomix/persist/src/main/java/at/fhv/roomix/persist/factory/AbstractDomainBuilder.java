package at.fhv.roomix.persist.factory;

import at.fhv.roomix.persist.AbstractDao;
import at.fhv.roomix.persist.PersistLoadException;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.function.Supplier;

abstract class AbstractDomainBuilder <DM, EN>{

    protected static Logger logger = Logger.getLogger(Logger.class);

    /**
     * Map a collection to an other collection with different types
     * @param sourceModels Source Collection Model
     * @param destinationType Destination Collection Type
     * @param <S> Source Type
     * @param <D> Destination Type
     * @return
     */
    private static  <S, D> Collection<D> mapCollection(Collection<S> sourceModels, Class<D> destinationType){
        ModelMapper singleModelMapper = new ModelMapper();
        Collection<D> destinationCollection = new LinkedList<>();
        for(S sourceModel : sourceModels){
            D destinationModel = singleModelMapper.map(sourceModel, destinationType);
            destinationCollection.add(destinationModel);
        }
        return destinationCollection;
    }

    protected void put(Class destinationType,
                       ISourceMapper<Collection> sourceMapper,
                       IDestinationMapper<Collection> destinationMapper,
                              LinkedHashMap<ISourceMapper<Collection>,
                                      Map.Entry<
                                              Class,
                                              IDestinationMapper<Collection>>
                                      > mapping)
    {

        if(mapping == null){
            throw new IllegalArgumentException("Hash Map for Domain Builder can't be null");
        }
        mapping.put(sourceMapper,
                new AbstractMap.SimpleEntry<> (destinationType, destinationMapper));
    }

    protected  void  mapAllCollections(
            LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping)
    {
        mapping.forEach((o, o2) -> {
            Collection destinationCollection = mapCollection(o.map(), o2.getKey());
            o2.getValue().set(destinationCollection);
        });
    }

    // EN = Entity, DM = Domain (object)
    protected DM getById(int id, Class<EN> entityClass) {
        Supplier daoInstanceSupplier = AbstractDao.getDaoInstanceByEntityClass(entityClass);
        if(daoInstanceSupplier == null){
            throw new IllegalStateException("DAO Instance couldn't be retrieved");
        } else{
            AbstractDao generalDataAccessObject = (AbstractDao) daoInstanceSupplier.get();
            EN entity = null;
            try {
                entity = (EN) generalDataAccessObject.load(id);
            } catch (PersistLoadException e) {
                e.printStackTrace();
            }
            return mapEntityToDomain(entity);
        }
    }


    protected abstract DM mapEntityToDomain(EN entity);
    protected abstract DM get(int id);
    protected abstract List<DM> getAll();
    protected abstract void set(DM domainObject);

}