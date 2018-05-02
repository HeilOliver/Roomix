package at.fhv.roomix.domain.common;

/**
 * Roomix
 * at.fhv.roomix.domain.common
 * ILazyLoader
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public interface ILazyLoader<T, V> {
    T get(V obj) throws Exception;
}
