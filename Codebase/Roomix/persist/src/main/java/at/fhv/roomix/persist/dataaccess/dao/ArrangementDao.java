package at.fhv.roomix.persist.dataaccess.dao;

import at.fhv.roomix.persist.models.ArticleEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.dataaccess.dao
 * ArrangementDao
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ArrangementDao extends AbstractDao<ArticleEntity, Integer> {

    public ArrangementDao() {
        super(ArticleEntity.class);
    }

}
