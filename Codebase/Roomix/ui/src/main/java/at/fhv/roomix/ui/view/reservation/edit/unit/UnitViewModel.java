package at.fhv.roomix.ui.view.reservation.edit.unit;

import at.fhv.roomix.controller.reservation.model.ArticlePojo;
import at.fhv.roomix.controller.reservation.model.ReservationUnitPojo;
import at.fhv.roomix.controller.reservation.model.RoomCategoryPojo;
import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.common.validator.DateValidator;
import at.fhv.roomix.ui.dataprovider.ReadOnlyReservationProvider;
import at.fhv.roomix.ui.view.reservation.edit.ReservationEditScope;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.utils.itemlist.ListTransformation;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.FunctionBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import de.saxsys.mvvmfx.utils.validation.Validator;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.unit
 * UnitViewModel
 * 21/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class UnitViewModel extends SubscribeAbleViewModel<ReservationUnitPojo> {

    private final ReadOnlyReservationProvider provider;
    private final ObservableList<CategoryItemViewModel> roomCategories;

    @InjectResourceBundle
    private ResourceBundle resourceBundle;
    @InjectScope
    private ReservationEditScope scope;

    private StringProperty duration = new SimpleStringProperty();
    private CompositeValidator formValidator = new CompositeValidator();
    private StringProperty arrivalTime = new SimpleStringProperty();
    private ObservableList<PacketsItemViewModel> articleList;

    public UnitViewModel() {
        provider = new ReadOnlyReservationProvider();

        ListTransformation<RoomCategoryPojo, CategoryItemViewModel> transRoomCategory
                = new ListTransformation<>(provider.getPossibleCategories(), CategoryItemViewModel::new);
        roomCategories = transRoomCategory.getTargetList();

        ListTransformation<ArticlePojo, PacketsItemViewModel> transArticle
                = new ListTransformation<>(provider.getPossibleArticles(), PacketsItemViewModel::new);
        articleList = transArticle.getTargetList();
    }

    ObjectProperty<LocalDate> arrivalDateProperty() {
        return pojoWrapper.field("arrivalDate",
                ReservationUnitPojo::getStartDate, ReservationUnitPojo::setStartDate);
    }

    ObjectProperty<LocalDate> departureDateProperty() {
        return pojoWrapper.field("departureDate",
                ReservationUnitPojo::getEndDate, ReservationUnitPojo::setEndDate);
    }

    IntegerProperty categoryProperty() {
        return pojoWrapper.field("roomCategory",
                ReservationUnitPojo::getRoomCategory, ReservationUnitPojo::setRoomCategory);
    }

    private ObjectProperty<LocalTime> arrivalTime() {
        return pojoWrapper.field("arrivalTime",
                ReservationUnitPojo::getArrivalTime, ReservationUnitPojo::setArrivalTime);
    }

    StringProperty arrivalTimeProperty() {
        return arrivalTime;
    }

    ReadOnlyStringProperty durationProperty() {
        return duration;
    }

    private void calculateDuration() {
        if (arrivalDateProperty().get() == null ||
                departureDateProperty().get() == null) {
            duration.setValue("?");
            return;
        }
        Duration days = Duration.ofDays(ChronoUnit.DAYS.between(arrivalDateProperty().get(), departureDateProperty().get()));
        duration.setValue(days.toDays() + " " + StringResourceResolver.getStaticResolve(resourceBundle, "reservation.days"));

        provider.loadCategories(arrivalDateProperty().get(), departureDateProperty().get(), scope.getCurrContractingParty());
    }

    public void initialize() {
        arrivalDateProperty().addListener((observable) -> calculateDuration());
        departureDateProperty().addListener((observable) -> calculateDuration());

        arrivalTime.addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            String trimmedString = newValue.trim();

            if (trimmedString.isEmpty()) return;
            if (!trimmedString.matches("^(\\d)+$")) {
                arrivalTime.setValue(oldValue);
                return;
            }

            int i = Integer.parseInt(trimmedString);
            if (i > 24 || i < 0) {
                arrivalTime.setValue(oldValue);
                return;
            }
            LocalTime parse;
            if (i < 10) {
                parse = LocalTime.parse("0" + i + ":00:00");
            } else {
                parse = LocalTime.parse(i + ":00:00");
            }
            arrivalTime().setValue(parse);
        }));

        Validator arrivalDateValidator = new DateValidator(arrivalDateProperty());
        Validator departureDateValidator = new DateValidator(departureDateProperty());
        Validator arrivalTimeValidator = new FunctionBasedValidator<>(
                arrivalTime(), Objects::nonNull, ValidationMessage.error(""));

        formValidator = new CompositeValidator(
                arrivalDateValidator,
                departureDateValidator,
                arrivalTimeValidator
        );

        isValid = formValidator.getValidationStatus().validProperty();
    }

    @Override
    protected void afterSubscribe(boolean isNew) {
        provider.clear();
        provider.loadArticles();
    }

    public ObservableList<PacketsItemViewModel> getArticleList() {
        return articleList;
    }

    ObservableList<CategoryItemViewModel> getRoomCategories() {
        return roomCategories;
    }
}
