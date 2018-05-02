package at.fhv.roomix.persist.builder.dependencybuilder;

import at.fhv.roomix.domain.common.ProxyCollection;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import at.fhv.roomix.domain.stay.CategoryFinder;
import at.fhv.roomix.persist.builder.accessbuilder.PartnerAgreementBuilder;
import at.fhv.roomix.persist.builder.accessbuilder.ReservationUnitBuilder;

/**
 * Roomix
 * at.fhv.roomix.persist.builder.dependencybuilder
 * CategoryFinderBuilder
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CategoryFinderBuilder {

    public static CategoryFinder create() {
        CategoryFinder finder = new CategoryFinder();
        finder.setUnits(new ProxyCollection<>(ReservationUnitBuilder::getAll));
        finder.setAgreementSupplier(PartnerAgreementBuilder::getAgreementsFor);
        return finder;
    }
}
