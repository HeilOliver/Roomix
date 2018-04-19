package at.fhv.roomix.domain.guest.model;

import java.util.Collection;

/**
 * Interface so you can use a Domain Builder as Proxy. You can use
 * this interface to lazy load a Collection/Instance of referenced Entities
 * to domain objects.
 * @param <D> Lazy loading domain type
 * @param <PK> Primary key type
 */
public interface IProxy<D, PK> {
    D lazyLoadInstance(PK key);
    Collection<D> lazyLoadCollection(PK key);
}
