package at.fhv.roomix.ui.view.checkin.edit.unit;

import at.fhv.roomix.controller.model.*;
import at.fhv.roomix.ui.common.LabelBuilder;
import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.dataprovider.UnitCheckInProvider;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItemViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.utils.itemlist.ListTransformation;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class UnitViewModel extends SubscribeAbleViewModel<ReservationUnitPojo> {

    private ObservableList<PacketsItemViewModel<ArrangementPojo>> arrangementList = FXCollections.emptyObservableList();
    private ObservableList<PacketsItemViewModel<PersonPojo>> personList = FXCollections.emptyObservableList();
    private BooleanProperty listChanged = new SimpleBooleanProperty();
    private BooleanProperty personListChanged = new SimpleBooleanProperty();

    private SimpleStringProperty arrivalDate = new SimpleStringProperty();
    private SimpleStringProperty endDate = new SimpleStringProperty();
    private SimpleStringProperty arrivalTime = new SimpleStringProperty();
    private SimpleStringProperty category = new SimpleStringProperty();
    private SimpleStringProperty roomNumber = new SimpleStringProperty(); // TODO: get from pojo and add binding
    private SimpleBooleanProperty commitButtonDisabledProperty = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty showCheckInInformation = new SimpleBooleanProperty(false);
    private SimpleStringProperty statusText = new SimpleStringProperty();
    private SimpleStringProperty checkInRoomNumber = new SimpleStringProperty();


    @InjectScope
    private ReservationViewScope scope;
    @InjectResourceBundle
    private ResourceBundle resourceBundle;

    public UnitViewModel(){

    }

    public ObservableList<PacketsItemViewModel<ArrangementPojo>> getArrangementList() {
        return arrangementList;
    }

    public ObservableList<PacketsItemViewModel<PersonPojo>> getPersonList() {
        return personList;
    }

    public void initialize(){
        scope.subscribe(ReservationViewScope.commandOnChange, (s, objects) -> {
            Collection<PersonPojo> persons = scope.selectedPojoProperty().get().getPersons();
            if (persons != null) {
                LinkedList<PersonPojo> personListL = new LinkedList<>(persons);
                ObservableList<PersonPojo> list = FXCollections.observableList(personListL);
                ListTransformation<PersonPojo, PacketsItemViewModel<PersonPojo>> transPersons
                        = new ListTransformation<>(list, (PersonPojo pojo) -> new PacketsItemViewModel<>(pojo, LabelBuilder.getPersonILabelBuilder()));
                ObservableList<PacketsItemViewModel<PersonPojo>> targetList = transPersons.getTargetList();
                personList = targetList;
                personListChanged.setValue(!personListChangedProperty().getValue());
            }
        });
    }

    public void fireChange(){
        scope.publish(ReservationViewScope.commandOnChange);
    }

    // TODO: bereits eingecheckte Units anzeigen
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

    void onCommit(){
        /* TODO: fill currModel Pojo with commited information */
        UnitCheckInProvider provider = new UnitCheckInProvider();
        CheckInPojo checkInPojo = new CheckInPojo();
        checkInPojo.setUnit(currModel.get());
        Collection<PersonPojo> assignedPersons = new LinkedList<>();
        for (PacketsItemViewModel<PersonPojo> personItem : personList) {
            if(personItem.isChecked()){
                assignedPersons.add(personItem.getPojo());
            }
        }
        checkInPojo.setAssignedPerson(assignedPersons);
        provider.doCheckIn(checkInPojo, reply -> {
            if(reply.getReplyMessage().equals(CheckInReply.Reply.OK)){
                statusText.setValue("");
            } else if(reply.getReplyMessage().equals(CheckInReply.Reply.DIRTY)){
                statusText.setValue(
                        StringResourceResolver.getStaticResolve(resourceBundle, "checkin.information.status.dirty"));
            } else if(reply.getReplyMessage().equals(CheckInReply.Reply.DOUBLE_OCCUPATION)){
                statusText.setValue(
                        StringResourceResolver.getStaticResolve(resourceBundle, "checkin.information.status.doubleocc")
                );
            }
            String roomNoLabel = StringResourceResolver.getStaticResolve(resourceBundle, "checkin.information.roomnumber");
            checkInRoomNumber.setValue(roomNoLabel + ": " + reply.getRoomNo());
            showCheckInInformation.setValue(true);
            commitButtonDisabledProperty.setValue(true);
            scope.publish(ReservationViewScope.commandOnCommit);
            commit();
        });
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
    public SimpleBooleanProperty commitButtonDisabledProperty() {
        return commitButtonDisabledProperty;
    }
    public SimpleBooleanProperty showCheckInInformationProperty() {
        return showCheckInInformation;
    }
    public SimpleStringProperty checkInRoomNumberProperty() {
        return checkInRoomNumber;
    }
    public SimpleStringProperty statusTextProperty() {
        return statusText;
    }
}
