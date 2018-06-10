package at.fhv.roomix.webLogin.security.controller;

import at.fhv.roomix.controller.common.exceptions.GetFault;
import at.fhv.roomix.controller.model.PricePojo;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.builder.accessbuilder.ContractingPartyBuilder;
import at.fhv.roomix.persist.builder.accessbuilder.RoomCategoryBuilder;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.webLogin.model.request.PriceCalc;
import at.fhv.roomix.webLogin.model.request.PriceRequest;
import org.modelmapper.MappingException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Roomix
 * at.fhv.roomix.webLogin.security.controller
 * ReservationPriceController
 * 04.06.2018 sge
 * <p>
 * Enter Description here
 */
@RestController
@RequestMapping("/ReservationPrice")
public class ReservationPriceController {

    @CrossOrigin()
    @RequestMapping(method = RequestMethod.POST)
    public PricePojo calculatePrice(@RequestBody PriceRequest priceRequest) throws GetFault {
        final ISessionDomain sessionHandler = SessionFactory.getInstance();

        int price = 0;
        PricePojo pricePojo = new PricePojo();

        for (PriceCalc priceCalc : priceRequest.getPriceRequests()) {
            int i = priceCalc.getAmount();
            while (i > 0) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                //convert String to LocalDate
                LocalDate startDate = LocalDate.parse(priceCalc.getStartDate(), formatter);
                LocalDate endDate = LocalDate.parse(priceCalc.getEndDate(), formatter);

                try {
                    // website guest default contactId 1
                    ContractingParty party = ContractingPartyBuilder.getByContact(1);
                    RoomCategory roomCategory = RoomCategoryBuilder.getRoomCategory(priceCalc.getCategoryID());

                    LocalDate currDate = startDate;
                    do {
                        price += roomCategory.calculatePrice(party, currDate);
                        currDate = currDate.plusDays(1);
                    } while (currDate.isBefore(endDate));

                    pricePojo.setPrice(price/100);
                } catch (BuilderLoadException | MappingException e) {
                    throw new GetFault("Cant load RoomCategories, see inner exception fore more details", e);
                }

                i--;
            }
        }
        // Gesamtpreis
        return pricePojo;
    }








}
