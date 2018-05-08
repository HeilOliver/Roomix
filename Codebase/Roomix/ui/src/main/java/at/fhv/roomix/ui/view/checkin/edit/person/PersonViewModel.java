package at.fhv.roomix.ui.view.checkin.edit.person;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.ui.view.checkin.edit.CheckInEditScope;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Collection;

public class PersonViewModel extends SubscribeAbleViewModel<ContactPojo> {

    @InjectScope
    private CheckInEditScope editScope;

    private SimpleStringProperty personFirstName = new SimpleStringProperty();
    private SimpleStringProperty personLastname = new SimpleStringProperty();

    public void initialize(){

    }

    public SimpleStringProperty personFirstNameProperty() {
        return personFirstName;
    }

    public SimpleStringProperty personLastnameProperty() {
        return personLastname;
    }

    @Override
    protected void afterSubscribe(boolean isNew) {
        personFirstName.setValue(currModel.get().getFirstName());
        personLastname.setValue(currModel.get().getLastName());
    }

    void onCommit(){
        currModel.get().setFirstName(personFirstName.get());
        currModel.get().setLastName(personLastname.get());
        commit();
    }
}
