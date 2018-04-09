package at.fhv.roomix.ui.views.contact.list;

import at.fhv.roomix.ui.views.contact.scope.ContactMasterDetailScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javax.inject.Singleton;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactDataView
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
@Singleton
@ScopeProvider(scopes = ContactMasterDetailScope.class)
public class ContactDataViewModel implements ViewModel {
    private BooleanProperty detailOpen = new SimpleBooleanProperty();

    @InjectScope
    private ContactMasterDetailScope scope;

    public void initialize() {
        detailOpen
                .bind(scope.selectedContactProperty().isNotNull());
    }

    public BooleanProperty detailOpenProperty() {
        return detailOpen;
    }
}
