package at.fhv.roomix.ui.common;

import at.fhv.roomix.controller.reservation.exeption.FaultException;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.ui.common
 * ISearchAble
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public interface ISearchAble<T> {
    Collection<T> search(String query) throws FaultException;
}
