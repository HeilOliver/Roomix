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

    private HashMap<String, MapTypeTuple> typeMap = new HashMap<>();

    private class MapTypeTuple<S, D>{
        private MapType<S, D> mapType;
        private MapTypeCall typeCall;

        private MapTypeTuple(MapType<S, D> mapType, MapTypeCall typeCall) {
            this.mapType = mapType;
            this.typeCall = typeCall;
        }
    }

    private enum MapTypeCall{
        MAP,
        REVERSE
    }

    public <S, D> void addMapType(MapType<S, D> mapType, Class<S> sourceType, Class<D> destinationType) {
        typeMap.put(sourceType.getName() + "|$|" + destinationType.getName(), new MapTypeTuple<>(mapType, MapTypeCall.MAP));
        typeMap.put(destinationType.getName() + "|$|" + sourceType.getName(), new MapTypeTuple<>(mapType, MapTypeCall.REVERSE));
    }

    public <S, D> void map(S source, D destination) {
        MapTypeTuple<S, D> type = findTypeFor(source, destination);

        switch (type.typeCall) {
            case MAP:
                type.mapType.map(source, destination, this);
                break;
            case REVERSE:
                @SuppressWarnings("unchecked") MapType<D, S> mapType = (MapType<D, S>) type.mapType;
                mapType.mapReverse(source, destination, this);
                break;
        }
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
    private <S, D> MapTypeTuple<S, D> findTypeFor(S source, D destination) {
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
