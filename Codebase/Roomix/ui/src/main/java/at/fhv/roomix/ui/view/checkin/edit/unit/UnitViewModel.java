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
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
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
    private SimpleBooleanProperty commitButtonDisabledProperty = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty showCheckInInformation = new SimpleBooleanProperty(false);
    private SimpleStringProperty statusText = new SimpleStringProperty();
    private SimpleStringProperty checkInRoomNumber = new SimpleStringProperty();
    private ObservableList<RoomSegment> roomSegments = FXCollections.observableArrayList();


    @InjectScope
    private ReservationViewScope scope;
    @InjectResourceBundle
    private ResourceBundle resourceBundle;


    public ObservableList<PacketsItemViewModel<ArrangementPojo>> getArrangementList() {
        return arrangementList;
    }

    public ObservableList<PacketsItemViewModel<PersonPojo>> getPersonList() {
        return personList;
    }

    public void initialize(){
    }

    public void fireChange(){
        scope.publish(ReservationViewScope.commandOnChange);
    }

    @Override
    protected void afterSubscribe(boolean isNew) {

        Collection<PersonPojo> persons = scope.getPersonHandler().getObjects();
        //Collection<PersonPojo> persons = scope.selectedPojoProperty().get().getPersons();
        if (persons != null) {
            LinkedList<PersonPojo> personListL = new LinkedList<>(persons);
            ObservableList<PersonPojo> list = FXCollections.observableList(personListL);
            ListTransformation<PersonPojo, PacketsItemViewModel<PersonPojo>> transPersons
                    = new ListTransformation<>(list, (PersonPojo pojo) -> new PacketsItemViewModel<>(pojo, LabelBuilder.getPersonILabelBuilder()));
            ObservableList<PacketsItemViewModel<PersonPojo>> targetList = transPersons.getTargetList();
            personList = targetList;
            personListChanged.setValue(!personListChangedProperty().getValue());
        }

        Collection<ArrangementPojo> arrangementPojos = currModel.get().getArrangements();
        if(arrangementPojos != null) {
            LinkedList<ArrangementPojo> arrangements = new LinkedList<>(arrangementPojos);
            ObservableList<ArrangementPojo> list = FXCollections.observableList(arrangements);
            ListTransformation<ArrangementPojo, PacketsItemViewModel<ArrangementPojo>> transArticle
                    = new ListTransformation<>(list, pojo -> new PacketsItemViewModel<>(pojo, LabelBuilder.getArrangementILabelBuilder()));
            ObservableList<PacketsItemViewModel<ArrangementPojo>> targetList = transArticle.getTargetList();
            arrangementList = targetList;
            targetList.forEach(PacketsItemViewModel::check);
            listChanged.setValue(true);
        }

        arrivalDate.setValue(DateTimeFormatter.ISO_LOCAL_DATE.format(currModel.get().getStartDate()));
        endDate.setValue(DateTimeFormatter.ISO_LOCAL_DATE.format(currModel.get().getEndDate()));
        arrivalTime.setValue(DateTimeFormatter.ISO_LOCAL_TIME.format(currModel.get().getArrivalTime()));
        category.setValue(currModel.get().getRoomCategory().getDescription());
        commitButtonDisabledProperty.setValue(currModel.get().isCheckedIn());

        Collection<RoomPojo> assignedRooms = currModel.get().getAssignedRooms();
        roomSegments.clear();
        for (RoomPojo roomPojo : assignedRooms) {
            if(roomPojo.getRoomNo() == null){
                String notAssigned = StringResourceResolver.getStaticResolve(resourceBundle, "checkin.information.notassigned");
                RoomSegment segment = new RoomSegment(1, notAssigned, "");
                roomSegments.add(segment);
                continue;
            }

                LocalDate startDate = roomPojo.getStartDate();
                LocalDate endDate = roomPojo.getEndDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                long daysBetween = Period.between(startDate, endDate).getDays();
                String dateString = startDate.format(formatter) + " - " + endDate.format(formatter);
                RoomSegment segment = new RoomSegment(daysBetween < 1 ? 1 : daysBetween, roomPojo.getRoomNo(), dateString);
                roomSegments.add(segment);

        }

    }

    void onCommit(){
        UnitCheckInProvider provider = new UnitCheckInProvider();
        CheckInPojo checkInPojo = new CheckInPojo();
        checkInPojo.setUnit(currModel.get());
        Collection<PersonPojo> assignedPersons = new LinkedList<>();
        for (PacketsItemViewModel<PersonPojo> personItem : personList) {
            if(personItem.isChecked()){
                assignedPersons.add(personItem.getPojo());
            }
        }
        if (assignedPersons.isEmpty()) {
            scope.publish(ReservationViewScope.commandSaveUpdateError, new Error("You must assign at least one Person"));
            return;
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
        }, errorMessage -> {
            scope.publish(ReservationViewScope.commandSaveUpdateError, new Error(errorMessage));
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
    public ObservableList<RoomSegment> roomSegmentsProperty() {
        return roomSegments;
    }
}
