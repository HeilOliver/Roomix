package at.fhv.roomix.persist.factory;

import at.fhv.roomix.domain.guest.model.ContactNoteDomain;
import at.fhv.roomix.persist.AbstractDao;
import org.modelmapper.ModelMapper;

import java.util.*;

public abstract class AbstractFactory<S, D> {

    private LinkedHashMap<ISourceMapper<Collection<S>>, Class<D>> mapping;


    /**
     * Map Collections to collection with different types
     * @param sourceModels Source Collection Model
     * @param destinationType Destination Collection Type
     * @param <S> Source Type
     * @param <D> Destination Type
     * @return
     */
    protected static  <S, D> Collection<D> mapCollection(Collection<S> sourceModels, Class<D> destinationType){
        ModelMapper singleModelMapper = new ModelMapper();
        Collection<D> destinationCollection = new LinkedList<>();
        for(S sourceModel : sourceModels){
            D destinationModel = singleModelMapper.map(sourceModel, destinationType);
            destinationCollection.add(destinationModel);
        }
        return destinationCollection;
    }

    protected void put(Class<D> destinationType, ISourceMapper<Collection<S>> sourceMapper, IDestinationMapper<Collection<D>> destinationMapper){
        if(mapping == null){
            mapping = new LinkedHashMap<>();
        }
        mapping.put(sourceMapper, destinationType);
    }

    protected void map(){
        mapping.forEach((o, o2) -> {
            Collection<D> destinationCollection = mapCollection(o.map(), o2);
        });
    }

}