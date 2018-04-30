package at.fhv.roomix.persistlayer.dataaccess;

import at.fhv.roomix.persistlayer.exception.PersistStateException;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.dataaccess
 * ISessionController
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public interface ISessionController {

    void startTransaction() throws PersistStateException;

    void commitTransaction();

    void closeSession();

    void rolBackTransaction();

}
