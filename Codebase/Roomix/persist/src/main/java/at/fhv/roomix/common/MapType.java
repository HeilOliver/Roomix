package at.fhv.roomix.common;

/**
 * Roomix
 * at.fhv.roomix.common
 * MapType
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public interface MapType<S, D> {
    void map(S source, D destination, Mapper mapper) throws MappingException;
}
