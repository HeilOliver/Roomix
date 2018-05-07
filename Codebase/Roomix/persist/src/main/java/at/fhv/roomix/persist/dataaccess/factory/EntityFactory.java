package at.fhv.roomix.persist.dataaccess.factory;


import at.fhv.roomix.persist.dataaccess.IDao;
import at.fhv.roomix.persist.dataaccess.ISessionController;
import at.fhv.roomix.persist.dataaccess.dao.HibernateSessionController;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.exception.PersistSaveException;
import at.fhv.roomix.persist.exception.PersistStateException;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * UebungMapper
 * at.fhv.teamf
 * EntityFactory
 * 25/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class EntityFactory<T, PK extends Serializable> {
    private static final LinkedList<EntityFactory> commitment
            = new LinkedList<>();
    private static ISessionController sessionController;

    static {
        sessionController = new HibernateSessionController();
    }

    private final Supplier<IDao<T, PK>> daoSupplier;
    private final int priority;
    private final LinkedList<T> toSave = new LinkedList<>();
    private final LinkedList<T> toDelete = new LinkedList<>();

    public EntityFactory(Supplier<IDao<T, PK>> daoSupplier, int priority) {
        this.priority = priority;
        this.daoSupplier = daoSupplier;
        commitment.add(this);
    }

    public static void commitAll() throws PersistStateException, PersistSaveException {
        try {
            sessionController.startTransaction();
            commitment.sort(Comparator.comparingInt(o -> o.priority));
            for (EntityFactory next : commitment) {
                next.doSave();
            }
            for (EntityFactory next : commitment) {
                next.doDelete();
            }
            for (EntityFactory next : commitment) {
                next.clear();
            }
            commitment.clear();
            sessionController.commitTransaction();
        } catch (PersistStateException | PersistSaveException e) {
            sessionController.rollBackTransaction();
            throw e;
        }
    }

    public static void stashChanges() {
    }

    public T getOrDefault(PK key, T _default) {
        try {
            T getObj = get(key);
            if (getObj != null) return getObj;
        } catch (PersistLoadException ignore) {
        }
        return _default;
    }

    public T get(PK key) throws PersistLoadException {
        IDao<T, PK> dao = daoSupplier.get();
        T load = null;
        try {
            load = dao.load(key);
        } catch (PersistStateException e) {
            throw new PersistLoadException("Internal error see inner Exception for details", e);
        }
        if (load == null) throw new PersistLoadException(
                String.format("No entity with the given Key found Key:%s Dao:%s",
                        key.toString(), dao.getClass().toString()));
        return load;
    }

    public List<T> getAll() throws PersistLoadException {
        IDao<T, PK> dao = daoSupplier.get();
        List<T> load = null;
        try {
            load = dao.loadAll();
        } catch (PersistStateException e) {
            throw new PersistLoadException("Internal error see inner Exception for details", e);
        }
        return load;
    }

    public void saveOrUpdate(T entity) {
        toSave.add(entity);
    }

    public void delete(T entity) {
        toDelete.add(entity);
    }

    private void doSave() throws PersistStateException, PersistSaveException {
        if (toSave.isEmpty()) return;
        IDao<T, PK> dao = daoSupplier.get();
        for (T entity : toSave) {
            dao.save(entity);
        }
    }

    private void doDelete() throws PersistStateException, PersistSaveException {
        if (toDelete.isEmpty()) return;
        IDao<T, PK> dao = daoSupplier.get();
        for (T entity : toDelete) {
            dao.delete(entity);
        }
    }

    private void clear() {
        toDelete.clear();
        toSave.clear();
    }
}
