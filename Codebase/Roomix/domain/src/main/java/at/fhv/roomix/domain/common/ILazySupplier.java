package at.fhv.roomix.domain.common;

public interface ILazySupplier<T> {
    T get() throws Exception;
}
