package at.fhv.roomix.persistlayer.dataaccess;


import at.fhv.roomix.persistlayer.dataaccess.dao.HibernateSessionController;
import at.fhv.roomix.persistlayer.exception.PersistLoadException;
import at.fhv.roomix.persistlayer.exception.PersistSaveException;
import at.fhv.roomix.persistlayer.exception.PersistStateException;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
    private static final PriorityQueue<EntityFactory> commitment
            = new PriorityQueue<>((Comparator.comparingInt(o -> o.priority)));
    private static ISessionController sessionController;
    private final Supplier<IDao<T, PK>> daoSupplier;
    private final int priority;
    private final LinkedList<T> toSave = new LinkedList<>();
    private final LinkedList<T> toDelete = new LinkedList<>();

    static {
        sessionController = new HibernateSessionController();
    }

    public EntityFactory(Supplier<IDao<T, PK>> daoSupplier, int priority) {
        this.priority = priority;
        this.daoSupplier = daoSupplier;
        commitment.add(this);
    }

    public static void commitAll() throws PersistStateException, PersistSaveException {
        try {
            sessionController.startTransaction();
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
            sessionController.rolBackTransaction();
            throw e;
        }
    }

    public T get(PK key) throws PersistLoadException {
        IDao<T, PK> dao = daoSupplier.get();
        T load = null;
        try {
            load = dao.load(key);
        } catch (PersistStateException e) {
            throw new PersistLoadException("Internal error see inner Exception for details", e);
        }
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
