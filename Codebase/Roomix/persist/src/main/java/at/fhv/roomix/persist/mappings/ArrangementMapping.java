package at.fhv.roomix.persist.mappings;

import at.fhv.roomix.common.MapType;
import at.fhv.roomix.common.Mapper;
import at.fhv.roomix.common.MappingException;
import at.fhv.roomix.domain.reservation.Arrangement;
import at.fhv.roomix.persist.models.ArticleEntity;

/**
 * Roomix
 * at.fhv.roomix.persist.mappings
 * ArrangementMapping
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ArrangementMapping implements MapType<ArticleEntity, Arrangement> {

    @Override
    public void map(ArticleEntity source, Arrangement destination, Mapper mapper) throws MappingException {
        destination.setId(source.getArticleId());
        destination.setDescription(source.getArticleDescription());
        destination.setPrice(source.getPrice());
    }
}
