package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.domain.guest.contractingparty.Agreement;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.dataaccess.factory.ContractingPartyFactory;
import at.fhv.roomix.persist.dataaccess.factory.RoomCategoryFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.ContractingPartyEntity;
import at.fhv.roomix.persist.models.PartnerAgreementEntity;
import at.fhv.roomix.persist.models.RoomCategoryEntity;
import at.fhv.roomix.persist.models.RoomCategoryPriceEntity;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * Roomix
 * at.fhv.roomix.persist.builder.accessbuilder
 * PartnerAgreementBuilder
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PartnerAgreementBuilder {
    private static final ModelMapper mapper = new ModelMapper();

    public static Collection<Agreement> getAgreementsFor(ContractingParty party) throws BuilderLoadException {
        ContractingPartyEntity entity = null;
        try {
            entity = ContractingPartyFactory.getInstance().get(party.getId());
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }

        Collection<PartnerAgreementEntity> agreements = entity.getPartnerAgreements();
        HashSet<Agreement> resultSet = new HashSet<>();
        for (PartnerAgreementEntity agreementEntity : agreements) {
            Agreement agreement = mapper.map(agreementEntity, Agreement.class);
            agreement.setCategory(RoomCategoryBuilder.getRoomCategory(
                    agreementEntity.getRoomCategoryByRoomCategory().getRoomCategoryId()));
            resultSet.add(agreement);
        }

        return resultSet;
    }

    static Agreement getAgreement(LocalDate date, RoomCategory roomCategory, ContractingParty party) {
        RoomCategoryEntity categoryEntity;
        try {
            categoryEntity = RoomCategoryFactory.getInstance().get(roomCategory.getId());
        } catch (PersistLoadException e) {
            throw new RuntimeException();
        }
        Optional<RoomCategoryPriceEntity> priceEntity = categoryEntity.getRoomCategoryPrices().stream()
                .filter((p) -> p.getSeason().getStartDate().toLocalDate().isBefore(date))
                .filter((p) -> p.getSeason().getEndDate().toLocalDate().isAfter(date))
                .findFirst();

        if (!priceEntity.isPresent()) return null;


        return null;
    }

}
