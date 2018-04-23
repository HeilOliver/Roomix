package at.fhv.roomix.persist.factory;

import at.fhv.roomix.persist.AbstractDao;
import at.fhv.roomix.persist.PersistLoadException;
import at.fhv.roomix.persist.PersistSaveException;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.function.Supplier;

/**
 * Abstract class for loading Entities from the database with DAOs and mapping them to Domain Objects
 *
 * @param <DM> Type of the corresponding domain object
 * @param <EN> Type of the corresponding entity
 */
abstract class AbstractDomainBuilder<DM, EN> {

    protected static Logger logger = Logger.getLogger(Logger.class);

    /**
     * Map a collection to an other collection with different types
     *
     * @param sourceModels    Source Collection Model
     * @param destinationType Destination Collection Type
     * @param <S>             Source Type
     * @param <D>             Destination Type
     * @return
     */
    private static <S, D> Collection<D> mapCollection(Collection<S> sourceModels, Class<D> destinationType) {
        ModelMapper singleModelMapper = new ModelMapper();
        Collection<D> destinationCollection = new HashSet<>();
        for (S sourceModel : sourceModels) {
            D destinationModel = singleModelMapper.map(sourceModel, destinationType);
            destinationCollection.add(destinationModel);
        }
        return destinationCollection;
    }

    /**
     * Add a mapping to a content of mappings for mapping an entity to a domain or the other way round
     *
     * @param destinationType   Destination class type (Entity.class, Domain.class)
     * @param sourceMapper      Anonymous implementation of the source getter (Entity::getPersonEntities)
     * @param destinationMapper Anonymous implementation of the destination setter (Domain::setPersonDomains)
     * @param mapping           Linked Hash Map where the mapping is saved
     */
    protected void put(Class destinationType, ISourceMapper<Collection> sourceMapper,
                       IDestinationMapper<Collection> destinationMapper,
                       LinkedHashMap<ISourceMapper<Collection>,
                               Map.Entry<Class, IDestinationMapper<Collection>>> mapping) {

        if (mapping == null) {
            throw new IllegalArgumentException("Hash Map for Domain Builder can't be null");
        }
        mapping.put(sourceMapper,
                new AbstractMap.SimpleEntry<>(destinationType, destinationMapper));
    }

    /**
     * Does the actual mapping, by a given content, that contains source and destination
     *
     * @param mapping Linked Hash Map with source getter, destination class and destination setter
     */
    protected void mapAllCollections(
            LinkedHashMap<ISourceMapper<Collection>,
                    Map.Entry<Class, IDestinationMapper<Collection>>> mapping) {
        mapping.forEach((o, o2) -> {
            if (o.map() != null) {
                Collection destinationCollection = mapCollection(o.map(), o2.getKey());
                o2.getValue().set(destinationCollection);
            }
        });
    }

    /**
     * Get mapped domain object for given entity class and ID
     *
     * @param id          Database primary key
     * @param entityClass References the Entity class the desired DAO generates
     * @return Mapped domain object
     */
    protected DM getById(int id, Class<EN> entityClass) {
        Supplier daoInstanceSupplier = AbstractDao.getDaoInstanceByEntityClass(entityClass);
        if (daoInstanceSupplier == null) {
            throw new IllegalStateException("DAO Instance couldn't be retrieved");
        } else {
            AbstractDao generalDataAccessObject = (AbstractDao) daoInstanceSupplier.get();
            EN entity = null;
            try {
                entity = (EN) generalDataAccessObject.load(id);
            } catch (PersistLoadException e) {
                logger.fatal(e.getMessage());
            }
            return mapEntityToDomain(entity);
        }
    }

    protected List<EN> loadAllEntites(Class<EN> entityClass){
        Supplier daoInstanceSupplier = AbstractDao.getDaoInstanceByEntityClass(entityClass);
        if (daoInstanceSupplier == null) {
            throw new IllegalStateException("DAO Instance couldn't be retrieved");
        } else {
            AbstractDao generalDataAccessObject = (AbstractDao) daoInstanceSupplier.get();
            List<EN> entities = null;
            try {
                entities = generalDataAccessObject.loadAll();
            } catch (PersistLoadException e) {
                logger.info(e.getMessage());
            }
            return entities;
        }
    }

    /**
     * Get all entries from the database as mapped domain objects. The domain object as specified in the generic
     * sub class (DM) will be returned.
     *
     * @param entityClass References the Entity class the desired DAO generates
     * @return List of mapped domain objects
     */
    protected List<DM> loadAll(Class<EN> entityClass) {
        Supplier daoInstanceSupplier = AbstractDao.getDaoInstanceByEntityClass(entityClass);
        if (daoInstanceSupplier == null) {
            throw new IllegalStateException("DAO Instance couldn't be retrieved");
        } else {
            AbstractDao generalDataAccessObject = (AbstractDao) daoInstanceSupplier.get();
            List<EN> entities = null;
            try {
                entities = generalDataAccessObject.loadAll();
            } catch (PersistLoadException e) {
                logger.info(e.getMessage());
            }
            List<DM> domainObjects = new ArrayList<>();
            for (EN entity : entities) {
                domainObjects.add(mapEntityToDomain(entity));
            }
            return domainObjects;
        }
    }

    // TODO: move the daoInstanceSupplier to own method (code reuse)
    /**
     *
     * @param entityClass
     * @param key
     * @param referencedColumn
     * @return
     */
    protected List<DM> loadByForeignKey(Class<EN> entityClass, Integer key, String referencedColumn){
        Supplier daoInstanceSupplier = AbstractDao.getDaoInstanceByEntityClass(entityClass);
        if (daoInstanceSupplier == null) {
            throw new IllegalStateException("DAO Instance couldn't be retrieved");
        } else {
            AbstractDao generalDataAccessObject = (AbstractDao) daoInstanceSupplier.get();
            List<EN> entities = null;
            try {
                entities = generalDataAccessObject.loadByForeignKey(key, referencedColumn);
            } catch (PersistLoadException e) {
                logger.info(e.getMessage());
            }
            List<DM> domainObjects = new ArrayList<>();
            for (EN entity : entities) {
                domainObjects.add(mapEntityToDomain(entity));
            }
            return domainObjects;
        }
    }

    /**
     * Saves a domain object by mapping it to the entity given by the generic sub class (EN) - calls mapDomainToEntity
     *
     * @param entityClass References the Entity class the desired DAO generates
     * @param domain      Domain object that needs to be saved
     */
    protected void save(Class<EN> entityClass, DM domain) {
        Supplier daoInstanceSupplier = AbstractDao.getDaoInstanceByEntityClass(entityClass);
        if (daoInstanceSupplier == null) {
            throw new IllegalStateException("DAO Instance couldn't be retrieved");
        } else {
            AbstractDao generalDataAccessObject = (AbstractDao) daoInstanceSupplier.get();
            try {
                generalDataAccessObject.save(mapDomainToEntity(domain));
            } catch (PersistSaveException e) {
                logger.fatal(e);
            }
        }
    }

    /**
     * Map entity to domain object. Use a simple mapping via the model mapper and (optional) specify
     * explicit mappings via put. For executing the mapper for the explicit mappings call "mapAllCollections"
     *
     * @param entity Entity type as specified in the generic sub class (EN)
     * @return Returns domain object as specified in the generic sub class (DM)
     */
    protected abstract DM mapEntityToDomain(EN entity);

    /**
     * Map domain objects to entities. Use a simple mapping via the model mapper and (optional) specify
     * explicit mappings via put. For executing the mapper for the explicit mappings call "mapAllCollections"
     *
     * @param domain Domain object as specified in the generic sub class (DM)
     * @return Returns entity as specified in the generic sub class (EN)
     */
    protected abstract EN mapDomainToEntity(DM domain);

    /**
     * Wrapper for "getById"
     *
     * @param id Integer ID primary key
     * @return Domain Model by ID
     */
    public abstract DM get(int id);

    /**
     * Wrapper for "loadAll"
     *
     * @return List of all Domain models from the database
     */
    public abstract List<DM> getAll();

    /**
     * Wrapper for "save"
     *
     * @param domainObject Domain object to save
     */
    public abstract void set(DM domainObject);

}