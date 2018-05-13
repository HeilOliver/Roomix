package at.fhv.roomix.ui.view.checkin.edit.person;

import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import javafx.beans.property.SimpleStringProperty;

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
            editScope.selectedPojoProperty().get().getPersons().add(currModel.get());
        }
        editScope.publish(ReservationViewScope.commandOnChange);
        commit();
    }
}
