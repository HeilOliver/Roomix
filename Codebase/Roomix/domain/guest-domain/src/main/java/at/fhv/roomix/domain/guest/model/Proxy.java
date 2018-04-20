package at.fhv.roomix.domain.guest.model;

public class Proxy<T, PK> {

    private ILazySupplier<T, PK> supplier;
    private PK key;

    public Proxy(PK key, ILazySupplier<T, PK> supplier){
        this.supplier = supplier;
        this.key = key;
    }

    public T get(){
        return supplier.get(key);
    }
}
