package at.fhv.roomix.ui.view.checkin.edit;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.ReservationPojo;
import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.view.reservation.edit.contact.ContactView;
import at.fhv.roomix.ui.view.reservation.edit.item.IContentBuilder;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemHandlerSingle;
import at.fhv.roomix.ui.view.reservation.scope.EDataProvider;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

import java.util.ResourceBundle;

public class CheckInEditViewModel implements ViewModel {

    @InjectScope
    private ReservationViewScope viewScope;

    private ObjectProperty<ItemControlViewModel<ContactPojo>> contractingPartyProperty;
    private ObjectProperty<Parent> currentView = new SimpleObjectProperty<>();
    private ObjectProperty<ItemControlViewModel> currentSelection = new SimpleObjectProperty<>();

    @InjectResourceBundle
    private ResourceBundle bundle;


    private final IContentBuilder<ContactPojo> contactBuilder = (pojo -> {
        StringBuilder sb = new StringBuilder();

        if ((pojo.getFirstName() == null || pojo.getLastName() == null)) {
            sb.append(StringResourceResolver.getStaticResolve(bundle, "reservation.edit.contact.tag.noname"));
        } else {
            if (pojo.getCompanyName() == null) {
                sb.append(pojo.getFirstName());
                sb.append(" ");
                sb.append(pojo.getLastName());
            } else {
                sb.append(pojo.getCompanyName());
            }
        }
        sb.append(" - ");
        sb.append(pojo.getPlace());
        return sb.toString();
    });

    private final ItemHandlerSingle<ContactPojo> contractingPartyHandler = new ItemHandlerSingle<>(
            ContactView.class, contactBuilder, currentSelection, currentView, ContactPojo::new
    );

    ReadOnlyObjectProperty<ItemControlViewModel<ContactPojo>> getContractingPartyControl(){
        return contractingPartyHandler.currentItem();
    }

    ReadOnlyBooleanProperty isContractingPartyAddAble() {
        return contractingPartyHandler.isAddAble();
    }

    public void initialize(){
        viewScope.init(EDataProvider.ReservationProvider);
        contractingPartyHandler.hideDeleteButton();
        viewScope.selectedPojoProperty().addListener((observable, oldValue, newValue) -> {
            if(viewScope.selectedPojoProperty() != null) {
                ReservationPojo reservationPojo = viewScope.selectedPojoProperty().get();
                if(reservationPojo != null && reservationPojo.getContractingParty() != null) {
                    contractingPartyHandler.setObject(reservationPojo.getContractingParty());
                }
            }
        });
    }
    public ObjectProperty<ItemControlViewModel<ContactPojo>> contractingPartyPropertyProperty() {
        return contractingPartyProperty;
    }

}
