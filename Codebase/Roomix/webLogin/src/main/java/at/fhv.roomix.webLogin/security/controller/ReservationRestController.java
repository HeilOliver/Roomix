package at.fhv.roomix.webLogin.security.controller;

import at.fhv.roomix.controller.common.exceptions.*;
import at.fhv.roomix.controller.contact.ContactControllerFactory;
import at.fhv.roomix.controller.contact.IContactController;
import at.fhv.roomix.controller.model.*;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.reservation.PaymentType;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.builder.accessbuilder.*;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.webLogin.model.CreditcardPojo;
import at.fhv.roomix.webLogin.model.request.CategoryRequest;
import at.fhv.roomix.webLogin.model.request.ReservationRequest;
import org.modelmapper.MappingException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/DoReservation")
public class ReservationRestController {

    @CrossOrigin()
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<InputStreamResource> doReservation(@RequestBody ReservationRequest reservationRequest) throws BuilderLoadException, ValidationFault, ArgumentFaultException, SessionFaultException, SaveFault, GetFault, IOException {
        //convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(reservationRequest.getGetStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(reservationRequest.getGetEndDate(), formatter);

        //Getting ContactPojo
        ContactPojo contactPojo = mapContact(reservationRequest.getFname(), reservationRequest.getLname(), reservationRequest.geteMail(), reservationRequest.getPostCode(), reservationRequest.getPlace(), reservationRequest.getCountry(),
                reservationRequest.getPhoneNumber(), reservationRequest.getStreet(), reservationRequest.getHousenumber(), reservationRequest.getCreditcard());

        //Mapping Unit
        Collection<ReservationUnitPojo> reservationUnitPojoCollection = new HashSet<>();
        for (CategoryRequest categoryRequest : reservationRequest.getCategoryRequests()) {
            int i = categoryRequest.getAmount();
            while (i > 0) {
                int price = 0;
                PricePojo pricePojo = new PricePojo();
                RoomCategory roomCategory = RoomCategoryBuilder.getRoomCategory(categoryRequest.getCategoryID());
                try {
                    // website guest default contactId 1
                    ContractingParty party = ContractingPartyBuilder.getByContact(1);

                    LocalDate currDate = startDate;
                    do {
                        price += roomCategory.calculatePrice(party, currDate);
                        currDate = currDate.plusDays(1);
                    } while (currDate.isBefore(endDate));

                    pricePojo.setPrice(price/100);
                } catch (BuilderLoadException | MappingException e) {
                    throw new GetFault("Cant load RoomCategories, see inner exception fore more details", e);
                }

                RoomCategoryPojo roomCategoryPojo = new RoomCategoryPojo();
                roomCategoryPojo.setId(roomCategory.getId());
                roomCategoryPojo.setDescription(roomCategory.getDescription());
                ReservationUnitPojo reservationUnitPojo = new ReservationUnitPojo();
                reservationUnitPojo.setEndDate(endDate);
                reservationUnitPojo.setStartDate(startDate);
                reservationUnitPojo.setRoomCategory(roomCategoryPojo);
                reservationUnitPojoCollection.add(reservationUnitPojo);
                reservationUnitPojo.setPrice(pricePojo);
                i--;
            }
        }

        PaymentTypePojo paymentTypePojo = new PaymentTypePojo();
        PaymentType paymentType =  PaymentTypeBuilder.getPaymentType(1);
        paymentTypePojo.setId(paymentType.getId());
        paymentTypePojo.setDescription(paymentType.getDescription());

        Collection<PersonPojo> personPojos = new HashSet<>();

        //Mapping reservation
        ReservationPojo reservationPojo = new ReservationPojo();
        reservationPojo.setContractingParty(contactPojo);
        reservationPojo.setUnits(reservationUnitPojoCollection);
        reservationPojo.setPaymentType(paymentTypePojo);
        reservationPojo.setPersons(personPojos);
        try {
            String pdfFilePath = ReservationControllerFactory.getInstance().updateReservation(-1000, reservationPojo);
            // generate pdf out of reservation confirmation
            File file = new File(pdfFilePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
            headers.add("Access-Control-Allow-Headers", "Content-Type");
            headers.add("Content-Disposition", "filename=" + file.getName());
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            headers.setContentLength(file.length());
            return new ResponseEntity<>(
                    new InputStreamResource(new FileInputStream(file)), headers, HttpStatus.OK);

        } catch (SessionFaultException | ArgumentFaultException | SaveFault | ValidationFault e) {
            throw new RuntimeException();
        }
    }

    public ContactPojo mapContact(String fname, String lname,String eMail,String postCode, String place, String country, String phoneNumber, String street,String housenumber,String creditcard) throws ArgumentFaultException, SessionFaultException, ValidationFault, SaveFault, GetFault {

        ContactPojo contactPojo = new ContactPojo();
        if(checkCreditcardValidation(creditcard)){
            CreditcardPojo creditcardPojo = new CreditcardPojo(creditcard,checkCreditcardType(creditcard));
        }

        //Mapping Contact
        contactPojo.setFirstName(fname);
        contactPojo.setLastName(lname);
        contactPojo.setEmail(eMail);
        contactPojo.setPhoneNumber(phoneNumber);
        contactPojo.setCountry(country);
        contactPojo.setPlace(place);
        contactPojo.setPostcode(postCode);
        contactPojo.setContractingPartyType(100);
        contactPojo.setStreet(street);
        contactPojo.setHouseNumber(housenumber);

        return contactPojo;
    }

    public boolean checkCreditcardValidation(String creditcard){
        int i,d=0,sum=0,card[]=new int[creditcard.length()];

        for(i=creditcard.length()-1;i>=0;i--)
        {
            if(creditcard.charAt(i)==' ')
                continue;
            else
                card[d++]=creditcard.charAt(i)-'0';
        }

        for(i=0;i<d;i++)
        {
            if(i%2!=0)
            {
                card[i]=card[i]*2;
                if(card[i]>9)
                    sum+=digSum(card[i]);
                else
                    sum+=card[i];
            }
            else
                sum+=card[i];
        }
        if(sum%10==0)
            return true;
        else
            return false;
    }

    public String checkCreditcardType(String creditcard){
        int AM=Integer.parseInt(creditcard.substring(0,2));
        int D=Integer.parseInt(creditcard.substring(0,4)),d=0;
        for(int i=creditcard.length()-1;i>=0;i--)
        {
            if(creditcard.charAt(i)==' ')
                continue;
            else
                d++;
        }
        if((AM==34 || AM==37) && d==15)
            System.out.println("AMEX");
        else if(D==6011 && d==16)
            System.out.println("Discover");
        else if(AM>=51 && AM<=55 && d==16)
            System.out.println("MasterCard");
        else if(((creditcard.charAt(0)-'0')==4)&&(d==13 || d==16))
            System.out.println("Visa");
        else
            return "U";
        return "";

    }

    public int digSum(int n)
    {
        int sum=0;
        while(n>0)
        {
            sum+=n%10;
            n/=10;
        }
        return sum;
    }

}
