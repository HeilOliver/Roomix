package at.fhv.roomix.webLogin.security.controller;

import at.fhv.roomix.controller.common.exceptions.ArgumentFaultException;
import at.fhv.roomix.controller.common.exceptions.GetFault;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.common.exceptions.ValidationFault;
import at.fhv.roomix.controller.model.CategoryDataPojo;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.model.RoomCategoryPojo;
import at.fhv.roomix.domain.session.ISessionDomain;
import at.fhv.roomix.domain.session.SessionFactory;
import at.fhv.roomix.ui.dataprovider.LoginProvider;
import at.fhv.roomix.webLogin.model.security.FreeRoomPojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

@RestController
@RequestMapping("/ReservationCheck")
public class ReservationCheckRestController {

    @RequestMapping(method = RequestMethod.POST)
    public Collection<FreeRoomPojo> doReservationCheck(@RequestParam(value="startDate") String getStartDate,@RequestParam(value="endDate") String getEndDate) throws GetFault, SessionFaultException, ArgumentFaultException, ValidationFault {
        final ISessionDomain sessionHandler = SessionFactory.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        //convert String to LocalDate
        LocalDate startDate = LocalDate.parse(getStartDate, formatter);
        LocalDate endDate = LocalDate.parse(getEndDate, formatter);

        Collection<FreeRoomPojo> freeRoomPojoCollection = new HashSet<>();


        for (RoomCategoryPojo roomCategoryPojo : ReservationControllerFactory.getInstance()
                .getAllCategory(LoginProvider.getSessionID())) {

            Collection<CategoryDataPojo> categoryDataPojoCollection =
                    ReservationControllerFactory.getInstance()
                            .calculateData(LoginProvider.getSessionID(),roomCategoryPojo,null,startDate,endDate);

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
