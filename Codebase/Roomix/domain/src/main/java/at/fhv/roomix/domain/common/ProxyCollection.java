package at.fhv.roomix.domain.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Roomix
 * at.fhv.roomix.domain.common
 * ProxyCollection
 * 02/05/2018 Oliver
 * <p>
 * An simple collection that lazy loads data. The collection is based on an HashSet
 */
public class ProxyCollection<T> extends HashSet<T> {

    private final ILazySupplier<Collection<T>> supplier;
    private boolean loaded;

    public ProxyCollection(ILazySupplier<Collection<T>> supplier) {
        this.supplier = supplier;
    }

    private void loadData() {
        if (loaded) return;
        try {
            addAll(supplier.get());
        } catch (Exception e) {
            throw new ProxyLoadException();
        }
        loaded = true;
    }

    @Override
    public Iterator<T> iterator() {
        loadData();
        return super.iterator();
    }

    @Override
    public int size() {
        loadData();
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        loadData();
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        loadData();
        return super.contains(o);
    }

    @Override
    public boolean add(T t) {
        loadData();
        return super.add(t);
    }

    @Override
    public boolean remove(Object o) {
        loadData();
        return super.remove(o);
    }

    @Override
    public void clear() {
        loadData();
        super.clear();
    }

    @Override
    public Object clone() {
        loadData();
        return super.clone();
    }

    @Override
    public Spliterator<T> spliterator() {
        loadData();
        return super.spliterator();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        loadData();
        return super.removeAll(c);
    }

    @Override
    public Object[] toArray() {
        loadData();
        return super.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        loadData();
        return super.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        loadData();
        return super.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        loadData();
        return super.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        loadData();
        return super.retainAll(c);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        loadData();
        return super.removeIf(filter);
    }

    @Override
    public Stream<T> stream() {
        loadData();
        return super.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        loadData();
        return super.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        loadData();
        super.forEach(action);
    }
}
