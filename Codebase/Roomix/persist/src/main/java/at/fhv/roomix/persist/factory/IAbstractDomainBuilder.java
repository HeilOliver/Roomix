package at.fhv.roomix.persist.factory;

import java.util.List;

public interface IAbstractDomainBuilder<DM, EN> {
    DM get(int id);

    List<DM> getAll();

    void set(DM domainObject);
}
