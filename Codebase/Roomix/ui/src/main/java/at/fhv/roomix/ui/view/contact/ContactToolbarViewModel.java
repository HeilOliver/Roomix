package at.fhv.roomix.ui.view.contact;

import at.fhv.roomix.ui.common.AbstractToolbar;
import at.fhv.roomix.ui.view.contact.scopes.ContactViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.content
 * ContactToolbarViewModel
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactToolbarViewModel extends AbstractToolbar<ContactViewScope> implements ViewModel {

    @InjectScope
    private ContactViewScope viewScope;

    public void initialize() {
        super.initialize(viewScope);
    }
}
