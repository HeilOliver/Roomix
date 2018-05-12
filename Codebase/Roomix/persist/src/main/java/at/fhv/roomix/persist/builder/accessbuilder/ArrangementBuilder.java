package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.domain.reservation.Arrangement;
import at.fhv.roomix.persist.dataaccess.factory.ArrangementFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.mappings.ArrangementMapping;
import at.fhv.roomix.persist.models.ArticleEntity;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.HashSet;

/**
 * Roomix
 * at.fhv.roomix.persist.builder
 * ArrangementBuilder
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ArrangementBuilder {
    private static final Mapper mapper = Mapper.getInstance();

    static {
        Mapper.getInstance().addMapType(new ArrangementMapping(), ArticleEntity.class, Arrangement.class);
    }

    public static Collection<Arrangement> getArrangements() throws BuilderLoadException {
        Collection<ArticleEntity> all = null;
        try {
            all = ArrangementFactory.getInstance().getAll();
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        Collection<Arrangement> results = new HashSet<>();

        all.forEach((e) -> results.add(mapper.map(e, Arrangement.class)));
        return results;
    }

    public static Arrangement getArrangement(int id) throws BuilderLoadException {
        ArticleEntity entity = null;
        try {
            entity = ArrangementFactory.getInstance().get(id);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        return mapper.map(entity, Arrangement.class);
    }

}
