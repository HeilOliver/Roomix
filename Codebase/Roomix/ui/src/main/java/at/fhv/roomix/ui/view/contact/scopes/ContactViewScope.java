package at.fhv.roomix.ui.view.contact.scopes;

import at.fhv.roomix.controller.reservation.model.ContactPojo;
import at.fhv.roomix.ui.common.AbstractMDScope;
import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.dataprovider.AbstractSearchEditProvider;
import at.fhv.roomix.ui.dataprovider.ContactProvider;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.*;
import javafx.collections.ObservableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.scopes
 * ContactViewScope
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactViewScope extends AbstractMDScope<ContactPojo> implements Scope {

    public ContactViewScope() {
        super(ContactPojo::new, new ContactProvider());
    }
}
