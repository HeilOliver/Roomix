package at.fhv.roomix.ui.view.checkin.edit.unit;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.ArrangementPojo;
import at.fhv.roomix.controller.reservation.model.ReservationUnitPojo;
import at.fhv.roomix.ui.common.LabelBuilder;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.edit.unit.ILabelBuilder;
import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItemViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.utils.itemlist.ListTransformation;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;

public class UnitViewModel extends SubscribeAbleViewModel<ReservationUnitPojo> {

    private ObservableList<PacketsItemViewModel<ArrangementPojo>> arrangementList = FXCollections.emptyObservableList();
    private ObservableList<PacketsItemViewModel<ContactPojo>> personList = FXCollections.emptyObservableList();
    private BooleanProperty listChanged = new SimpleBooleanProperty();
    private BooleanProperty personListChanged = new SimpleBooleanProperty();

    private SimpleStringProperty arrivalDate = new SimpleStringProperty();
    private SimpleStringProperty endDate = new SimpleStringProperty();
    private SimpleStringProperty arrivalTime = new SimpleStringProperty();
    private SimpleStringProperty category = new SimpleStringProperty();


    @InjectScope
    private ReservationViewScope scope;

    public UnitViewModel(){

    }

    public ObservableList<PacketsItemViewModel<ArrangementPojo>> getArrangementList() {
        return arrangementList;
    }

    public ObservableList<PacketsItemViewModel<ContactPojo>> getPersonList() {
        return personList;
    }

    public void initialize(){
        scope.subscribe(ReservationViewScope.commandOnChange, (s, objects) -> {
            Collection<ContactPojo> persons = scope.selectedPojoProperty().get().getPersonReservationsByReservationId();
            if (persons != null) {
                LinkedList<ContactPojo> personListL = new LinkedList<>(persons);
                ObservableList<ContactPojo> list = FXCollections.observableList(personListL);
                ListTransformation<ContactPojo, PacketsItemViewModel<ContactPojo>> transPersons
                        = new ListTransformation<>(list, (ContactPojo pojo) -> new PacketsItemViewModel<>(pojo, LabelBuilder.getPersonILabelBuilder()));
                ObservableList<PacketsItemViewModel<ContactPojo>> targetList = transPersons.getTargetList();
                personList = targetList;
                personListChanged.setValue(!personListChangedProperty().getValue());
            }
        });
    }

    public void fireChange(){
        scope.publish(ReservationViewScope.commandOnChange);
    }

    @Override
    protected void afterSubscribe(boolean isNew) {
        Collection<ArrangementPojo> arrangementPojos = currModel.get().getArrangements();
        if(arrangementPojos != null) {
            LinkedList<ArrangementPojo> arrangements = new LinkedList<>(arrangementPojos);
            ObservableList<ArrangementPojo> list = FXCollections.observableList(arrangements);
            ListTransformation<ArrangementPojo, PacketsItemViewModel<ArrangementPojo>> transArticle
                    = new ListTransformation<>(list, pojo -> new PacketsItemViewModel<>(pojo, LabelBuilder.getArrangementILabelBuilder()));
            ObservableList<PacketsItemViewModel<ArrangementPojo>> targetList = transArticle.getTargetList();
            arrangementList = targetList;
            listChanged.setValue(true);
        }

        if(currModel.get() != null) {
            arrivalDate.setValue(DateTimeFormatter.ISO_LOCAL_DATE.format(currModel.get().getStartDate()));
            endDate.setValue(DateTimeFormatter.ISO_LOCAL_DATE.format(currModel.get().getEndDate()));
            arrivalTime.setValue(DateTimeFormatter.ISO_LOCAL_TIME.format(currModel.get().getArrivalTime()));
            category.setValue(currModel.get().getRoomCategory().getDescription());
        }
    }

    public BooleanProperty listChangedProperty() {
        return listChanged;
    }

    public BooleanProperty personListChangedProperty() { return personListChanged; }

    public SimpleStringProperty arrivalDateProperty() {
        return arrivalDate;
    }
    public SimpleStringProperty endDateProperty() {
        return endDate;
    }
    public SimpleStringProperty arrivalTimeProperty() {
        return arrivalTime;
    }
    public SimpleStringProperty categoryProperty() {
        return category;
    }
}
