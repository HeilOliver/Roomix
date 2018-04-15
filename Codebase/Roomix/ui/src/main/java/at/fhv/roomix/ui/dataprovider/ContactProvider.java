package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.reservation.ReservationControllerFactory;
import at.fhv.roomix.controller.reservation.exeption.ArgumentFaultException;
import at.fhv.roomix.controller.reservation.exeption.SessionFaultException;
import at.fhv.roomix.controller.reservation.exeption.ValidationFault;
import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.common.ICallable;
import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.common.ISearchAble;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * ContactProvider
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactProvider extends SearchProvider<ContactPojo> {

    public ContactProvider(IErrorCall onSearchError) {
        super(query -> ReservationControllerFactory.getInstance()
                .getSearchedContacts(LoginProvider.getSessionID(), query),
                onSearchError);
    }

    public void saveOrUpdate(ContactPojo pojo, IErrorCall onError, ICallable onSuccess) {
        submit(() -> {
            try {
                ReservationControllerFactory
                        .getInstance()
                        .updateContact(LoginProvider.getSessionID(), pojo);
                reSearch();
                Platform.runLater(onSuccess::call);
            } catch (SessionFaultException | ArgumentFaultException | ValidationFault e) {
                Platform.runLater(() ->onError.errorOccurred(new Error(e.getMessage())));
            }
        });
    }

    public ReadOnlyBooleanProperty getInProcessProperty() {
        return inProcess();
    }
}
