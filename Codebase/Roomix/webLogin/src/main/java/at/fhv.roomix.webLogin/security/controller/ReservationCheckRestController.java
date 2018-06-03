package at.fhv.roomix.webLogin.security.controller;

import at.fhv.roomix.controller.implement.reservationcontroller.ReservationController;
import at.fhv.roomix.controller.model.RoomCategoryPojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
@RequestMapping("/ReservationCheck")
public class ReservationCheckRestController {
    @RequestMapping(method = RequestMethod.POST)
    public String getSaveContact(@RequestParam(value="startDate") String getStartDate,@RequestParam(value="endDate") String getEndDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        //convert String to LocalDate
        LocalDate startDate = LocalDate.parse(getStartDate, formatter);
        LocalDate endDate = LocalDate.parse(getEndDate, formatter);
        Collection roomCategoryPojoCollection = new Collection<RoomCategoryPojo>;
        ReservationController reservationController = new ReservationController();









        return "Hi";
    }
}
