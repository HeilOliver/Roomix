package at.fhv.roomix.domain.guest.model;

public interface ILazySupplier<T, PK> {
    T get(PK key);
}
