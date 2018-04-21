package at.fhv.roomix.ui.common.validator;


import at.fhv.roomix.controller.common.exceptions.FaultException;

/**
 * Roomix
 * at.fhv.roomix.ui.common.validator
 * IUpdateAble
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public interface IUpdateAble<T> {
    void update(T object) throws FaultException;
}
