package at.fhv.roomix.persist.builder.dependencybuilder;

import at.fhv.roomix.domain.guest.contractingparty.Agreement;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.payment.PriceCalculator;
import at.fhv.roomix.persist.builder.accessbuilder.PartnerAgreementBuilder;
import at.fhv.roomix.persist.exception.BuilderLoadException;

import java.util.Collection;

/**
 * Roomix
 * at.fhv.roomix.persist.builder.accessbuilder
 * PriceCalculatorBuilder
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PriceCalculatorBuilder {

    public static PriceCalculator get(ContractingParty party) throws BuilderLoadException {
        Collection<Agreement> agreements = PartnerAgreementBuilder.getAgreementsFor(party);
        PriceCalculator calculator = new PriceCalculator(agreements, null);

        return calculator;
    }
}
