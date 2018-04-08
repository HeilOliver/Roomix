package at.fhv.roomix.persist.factory;

import java.util.List;

public class AbstractDomainBuilderMock extends AbstractDomainBuilder {

    @Override
    protected Object mapEntityToDomain(Object entity) {
        return null;
    }

    @Override
    protected Object mapDomainToEntity(Object domain) {
        return null;
    }

    @Override
    public Object get(int id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void set(Object domainObject) {

    }
}
