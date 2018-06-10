package at.fhv.roomix.webLogin.security.controller;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.SaveFault;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.controller.model.ReservationUnitPojo;
import at.fhv.roomix.controller.model.RoomCategoryPojo;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.domain.room.RoomCategory;
import at.fhv.roomix.persist.builder.accessbuilder.RoomCategoryBuilder;
import at.fhv.roomix.persist.exception.BuilderLoadException;

import at.fhv.roomix.webLogin.model.CreditcardPojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;

@RestController
@RequestMapping("/DoReservation")
public class ReservationRestController {
    @RequestMapping(method = RequestMethod.POST)
    public void doReservation(@RequestParam(value="fname") String fname,
                              @RequestParam(value="lname") String lname,
                              @RequestParam(value="eMail") String eMail,
                              @RequestParam(value="postCode") String postCode,
                              @RequestParam(value="place") String place,
                              @RequestParam(value="country") String country,
                              @RequestParam(value="phoneNumber") String phoneNumber,
                              @RequestParam(value="street") String street,
                              @RequestParam(value="houesnumber") String housenumber,
                              @RequestParam(value="creditcard") String creditcard,
                              @RequestParam(value="categoryNumber") String[] categoryNumber,
                              @RequestParam(value="startDate") String getStartDate,
                              @RequestParam(value="endDate") String getEndDate) throws BuilderLoadException {


        //convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(getStartDate, formatter);
        LocalDate endDate = LocalDate.parse(getEndDate, formatter);

        //Getting ContactPojo
        ContactPojo contactPojo = mapContact(fname,lname,eMail,postCode,place,country,
                phoneNumber,street,housenumber,creditcard);

        //Mapping Unit
        Collection<ReservationUnitPojo> reservationUnitPojoCollection = new HashSet<>();

        for (String categoryID:categoryNumber) {
            int id = Integer.parseInt(categoryID);
            RoomCategory roomCategory= RoomCategoryBuilder.getRoomCategory(id);

            //Todo: Keine Ahnung ob das so geht!
            RoomCategoryPojo roomCategoryPojo = new RoomCategoryPojo();
            roomCategoryPojo.setId(roomCategory.getId());
            roomCategoryPojo.setDescription(roomCategory.getDescription());
            ReservationUnitPojo reservationUnitPojo = new ReservationUnitPojo();
            reservationUnitPojo.setEndDate(endDate);
            reservationUnitPojo.setStartDate(startDate);
            reservationUnitPojo.setRoomCategory(roomCategoryPojo);
            reservationUnitPojoCollection.add(reservationUnitPojo);
        }


        //Mapping reservation
        ReservationPojo reservationPojo = new ReservationPojo();
        reservationPojo.setContractingParty(contactPojo);
        reservationPojo.setUnits(reservationUnitPojoCollection);
        try {
            ReservationControllerFactory.getInstance().updateReservation(-1000,reservationPojo);
        } catch (SessionFaultException | ArgumentFaultException | SaveFault | ValidationFault e) {
            e.printStackTrace();
        }

    }

    public ContactPojo mapContact(String fname, String lname,String eMail,String postCode, String place, String country, String phoneNumber, String street,String housenumber,String creditcard) {

        ContactPojo contactPojo = new ContactPojo();
        //Todo: CreditcardPojo into Contact-Validation completed
        if(checkCreditcardValidation(creditcard)){
            CreditcardPojo creditcardPojo = new CreditcardPojo(creditcard,checkCreditcardType(creditcard));
            //contactPojo.setCreditCard(creditcardPojo);
        }
        //Mapping Contact
        contactPojo.setFirstName(fname);
        contactPojo.setLastName(lname);
        contactPojo.setEmail(eMail);
        contactPojo.setPhoneNumber(phoneNumber);
        contactPojo.setCountry(country);
        contactPojo.setPlace(place);
        contactPojo.setPostcode(postCode);
        //Todo: Check if it is possible (individual 0?)
        contactPojo.setContractingPartyType(0);
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
