package at.fhv.roomix.persist.dataaccess.factory;

import at.fhv.roomix.persist.dataaccess.dao.ArrangementDao;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.ArticleEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess
 * ArrangementFactory
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ArrangementFactory extends EntityFactory<ArticleEntity, Integer> {

    private static final Object lock = new Object();
    private static ArrangementFactory instance;

    private ArrangementFactory() {
        super(ArrangementDao::new, 1);
    }

    public static ArrangementFactory getInstance() {
        if (instance != null) return instance;
        synchronized (lock) {
            if (instance == null) {
                instance = new ArrangementFactory();
            }
        }
        return instance;
    }

    @Override
    public List<ArticleEntity> getAll() throws PersistLoadException {
        List<ArticleEntity> all = super.getAll();
        // TODO May here direct loading from db
        return all.stream().filter(
                (e) -> e.getArticleType()
                        .equals(ArticleEntity
                                .ArticleType.ARRANGEMENT.name()))
                .collect(Collectors.toList());
    }
}
