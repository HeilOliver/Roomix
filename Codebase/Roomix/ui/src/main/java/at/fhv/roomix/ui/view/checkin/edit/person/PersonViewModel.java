package at.fhv.roomix.ui.view.checkin.edit.person;

import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import javafx.beans.property.SimpleStringProperty;

import java.util.Collection;

public class PersonViewModel extends SubscribeAbleViewModel<PersonPojo> {

    @InjectScope
    private ReservationViewScope editScope;

    private SimpleStringProperty personFirstName = new SimpleStringProperty();
    private SimpleStringProperty personLastname = new SimpleStringProperty();

    private boolean isNew;

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
        personFirstName.setValue(currModel.get().getForeName());
        personLastname.setValue(currModel.get().getLastName());
        this.isNew = isNew;
    }

    void onCommit(){
        currModel.get().setForeName(personFirstName.get());
        currModel.get().setLastName(personLastname.get());
        if(isNew){
            pojoWrapper.field("firstname", PersonPojo::getForeName, PersonPojo::setForeName);
            pojoWrapper.field("lastname", PersonPojo::getLastName, PersonPojo::setLastName);
        } else {
            pojoWrapper.get().setForeName(personFirstName.get());
            pojoWrapper.get().setLastName(personLastname.get());
        }
        commit();
    }
}
