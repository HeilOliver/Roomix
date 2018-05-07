package at.fhv.roomix.domain.common;

public class Proxy<T> {
    private ILazySupplier<T> supplier;
    private T loadedData;

    public Proxy(ILazySupplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (loadedData == null) {
            try {
                loadedData = supplier.get();
            } catch (Exception e) {
                throw new ProxyLoadException();
            }
        }
        return loadedData;
    }
}
