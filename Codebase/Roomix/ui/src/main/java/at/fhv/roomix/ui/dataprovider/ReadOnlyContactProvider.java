package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.contact.ContactControllerFactory;
import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.ui.common.ISearchAble;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * ReadOnlyContactProvider
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReadOnlyContactProvider extends SearchProvider<ContactPojo> {
    public ReadOnlyContactProvider() {
        super(query -> ContactControllerFactory.getInstance()
                .getSearchedContacts(LoginProvider.getSessionID(), query));
    }
}
