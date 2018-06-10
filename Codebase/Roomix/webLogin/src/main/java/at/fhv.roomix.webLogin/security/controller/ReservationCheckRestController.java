package at.fhv.roomix.webLogin.security.controller;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.GetFault;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.model.CategoryDataPojo;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.model.RoomCategoryPojo;
import at.fhv.roomix.webLogin.model.FreeRoomPojo;
import at.fhv.roomix.webLogin.model.request.TimeSpan;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

@RestController
@RequestMapping("/ReservationCheck")
public class ReservationCheckRestController {
    
    @CrossOrigin()
    @RequestMapping(method = RequestMethod.POST)
    public Collection<FreeRoomPojo> doReservationCheck(@RequestBody TimeSpan timeSpan) throws GetFault, SessionFaultException, ArgumentFaultException, ValidationFault {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate startDate = LocalDate.parse(timeSpan.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(timeSpan.getEndDate(), formatter);

        Collection<FreeRoomPojo> freeRoomPojoCollection = new HashSet<>();


        for (RoomCategoryPojo roomCategoryPojo : ReservationControllerFactory.getInstance()
                .getAllCategory(-1000)) { //Session id hardcode

            Collection<CategoryDataPojo> categoryDataPojoCollection =
                    ReservationControllerFactory.getInstance()
                            .calculateData(-1000,roomCategoryPojo,null,startDate,endDate); //Session ID hardcode

            Optional<CategoryDataPojo> optionalDataPojo =
                    categoryDataPojoCollection.stream().min(Comparator.comparingInt(CategoryDataPojo::getFree));

            if (!optionalDataPojo.isPresent()) continue;
            if (optionalDataPojo.get().getFree() == 0) continue;

            FreeRoomPojo freeRoomPojo = new FreeRoomPojo(optionalDataPojo.get().getFree(),roomCategoryPojo.getDescription(),roomCategoryPojo.getId());
            freeRoomPojoCollection.add(freeRoomPojo);
        }
        return freeRoomPojoCollection;
    }
}
