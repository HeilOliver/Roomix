package at.fhv.roomix.webLogin.security.controller;

import at.fhv.roomix.controller.common.exceptions.GetFault;
import at.fhv.roomix.controller.common.exceptions.SessionFaultException;
import at.fhv.roomix.controller.model.RoomCategoryPojo;
import at.fhv.roomix.controller.reservation.IReservationController;
import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.ui.dataprovider.LoginProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/AllCategories")
public class GetAllCategoriesController {

    @RequestMapping(value="/AllCategories", method = RequestMethod.POST)
    public @ResponseBody Collection<RoomCategoryPojo> getAllCategories() {

        IReservationController instance = ReservationControllerFactory.getInstance();
        Collection<RoomCategoryPojo> collection = null;

        try {
            collection = instance.getAllCategory(LoginProvider.getSessionID());

            } catch (SessionFaultException e) {
                e.printStackTrace();
            } catch (GetFault getFault) {
                getFault.printStackTrace();
        }

        return collection;
    }

}
