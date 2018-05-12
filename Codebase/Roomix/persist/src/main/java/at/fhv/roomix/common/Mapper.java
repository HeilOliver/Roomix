package at.fhv.roomix.common;

import java.util.HashMap;

import static java.lang.ClassLoader.*;

/**
 * Roomix
 * at.fhv.roomix.common
 * Mapper
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class Mapper {
    private static final Object lock = new Object();
    private static Mapper instance;

    private Mapper() {
    }

    public static Mapper getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new Mapper();
            }
        }
        return instance;
    }

    private HashMap<String, MapType> typeMap = new HashMap<>();

    public <S, D> void addMapType(MapType<S, D> mapType, Class<S> sourceType, Class<D> destinationType) {
        typeMap.put(sourceType.getName() + "|$|" + destinationType.getName(), mapType);
    }

    public <S, D> void map(S source, D destination) {
        MapType<S, D> type = findTypeFor(source, destination);
        type.map(source, destination, this);
    }

    public <S, D> D map(S source, Class<D> destination) {
        D newClazz;
        try {
            @SuppressWarnings("unchecked")
            Class<D> aClass = (Class<D>) getSystemClassLoader().loadClass(destination.getName());
            newClazz = aClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new MappingException("Cant inizialize class");
        }

        map(source, newClazz);
        return newClazz;
    }

    @SuppressWarnings("unchecked")
    private <S, D> MapType<S, D> findTypeFor(S source, D destination) {
        String sourceType = source.getClass().getName();
        String destinationType = destination.getClass().getName();

        if (typeMap.containsKey(sourceType + "|$|" + destinationType))
            return typeMap.get(sourceType + "|$|" + destinationType);

        // May we want one of the super classes to be mapped
        Class<?> cls = source.getClass().getSuperclass();
        while(cls != null) {
            sourceType = cls.getName();
            if (typeMap.containsKey(sourceType + "|$|" + destinationType))
                return typeMap.get(sourceType + "|$|" + destinationType);
            cls = cls.getSuperclass();
        }

        throw new MappingException("No Mapping vor given Type");
    }
}
