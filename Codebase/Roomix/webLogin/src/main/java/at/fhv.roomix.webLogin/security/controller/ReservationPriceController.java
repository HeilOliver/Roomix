package at.fhv.roomix.webLogin.security.controller;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.GetFault;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.model.PricePojo;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.persist.builder.accessbuilder.ContractingPartyBuilder;
import at.fhv.roomix.persist.builder.accessbuilder.RoomCategoryBuilder;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.webLogin.model.request.PriceCalc;
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
    public PricePojo calculatePrice(@RequestBody PriceCalc priceCalc)
                                throws SessionFaultException, ValidationFault, ArgumentFaultException, GetFault {
        final ISessionDomain sessionHandler = SessionFactory.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //convert String to LocalDate
        LocalDate startDate = LocalDate.parse(priceCalc.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(priceCalc.getEndDate(), formatter);

        try {
            // website guest default contactId 1
            ContractingParty party = ContractingPartyBuilder.getByContact(1);
            int id = Integer.parseInt(priceCalc.getCategoryID());
            RoomCategory roomCategory = RoomCategoryBuilder.getRoomCategory(id);

            LocalDate currDate = startDate;
            int price = 0;
            do {
                price += roomCategory.calculatePrice(party, currDate);
                currDate = currDate.plusDays(1);
            } while (currDate.isBefore(endDate));

            int amount = Integer.parseInt(priceCalc.getAmount());
            if (amount < 1) amount = 1;
            price *= amount;
            PricePojo pricePojo = new PricePojo();
            pricePojo.setPrice(price/100);
            return pricePojo;
        } catch (BuilderLoadException | MappingException e) {
            throw new GetFault("Cant load RoomCategories, see inner exception fore more details", e);
        }
    }








}
