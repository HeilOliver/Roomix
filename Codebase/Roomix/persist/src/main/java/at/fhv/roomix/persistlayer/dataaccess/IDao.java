package at.fhv.roomix.persistlayer.dataaccess;

import at.fhv.roomix.persistlayer.exception.PersistLoadException;
import at.fhv.roomix.persistlayer.exception.PersistSaveException;
import at.fhv.roomix.persistlayer.exception.PersistStateException;

import java.io.Serializable;
import java.util.List;

/**
 * at.fhv.roomix.persist
 * IDao
 * 25/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public interface IDao<T, PK extends Serializable> {

    T load(PK id) throws IllegalArgumentException, PersistStateException, PersistLoadException;

    List<T> loadAll() throws PersistStateException, PersistLoadException;

    void save(T entity) throws IllegalArgumentException, PersistStateException, PersistSaveException;

    void delete(T entity) throws IllegalArgumentException, PersistStateException, PersistSaveException;
}
