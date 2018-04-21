package at.fhv.roomix.persist.factory;

import at.fhv.roomix.persist.factory.model.DeepTestDomain;
import at.fhv.roomix.persist.factory.model.DeepTestEntity;
import at.fhv.roomix.persist.factory.model.TestDomain;
import at.fhv.roomix.persist.factory.model.TestEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AbstractDomainBuilderMock extends AbstractDomainBuilder<TestDomain, TestEntity> {

    private static AbstractDomainBuilderMock instance;

    private AbstractDomainBuilderMock() {
    }

    public static AbstractDomainBuilderMock getInstance() {
        if (instance == null) {
            return (instance = new AbstractDomainBuilderMock());
        } else {
            return instance;
        }
    }

    @Override
    protected TestDomain mapEntityToDomain(TestEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<TestEntity, TestDomain>() {
            @Override
            protected void configure() {
                skip().setDoNotMap(null);
            }
        });
        TestDomain testDomain = modelMapper.map(entity, TestDomain.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();
        put(DeepTestDomain.class, entity::getCollection, testDomain::setCollection, mapping);
        mapAllCollections(mapping);

        return testDomain;
    }

    @Override
    protected TestEntity mapDomainToEntity(TestDomain domain) {
        ModelMapper modelMapper = new ModelMapper();
        TestEntity testEntity = modelMapper.map(domain, TestEntity.class);

        LinkedHashMap<ISourceMapper<Collection>,
                Map.Entry<Class, IDestinationMapper<Collection>>> mapping = new LinkedHashMap<>();
        put(DeepTestEntity.class, domain::getCollection, testEntity::setCollection, mapping);
        mapAllCollections(mapping);

        return testEntity;
    }


    public TestEntity injectDomain(TestDomain domain) {
        return mapDomainToEntity(domain);
    }

    public TestDomain injectEntity(TestEntity entity) {
        return mapEntityToDomain(entity);
    }

    @Override
    public TestDomain get(int id) {
        return null;
    }

    @Override
    public List<TestDomain> getAll() {
        return null;
    }

    @Override
    public void set(TestDomain domainObject) {

    }
}
