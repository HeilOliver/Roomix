package at.fhv.roomix.persist.dataaccess;

import at.fhv.roomix.persist.exception.PersistStateException;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * ISessionController
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public interface ISessionController {

    void startTransaction() throws PersistStateException;

    void commitTransaction();

    void closeSession();

    void rollBackTransaction();

}
