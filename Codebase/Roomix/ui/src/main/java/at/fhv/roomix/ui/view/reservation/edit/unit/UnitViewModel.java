package at.fhv.roomix.ui.view.reservation.edit.unit;

import at.fhv.roomix.controller.reservation.model.ArrangementPojo;
import at.fhv.roomix.controller.reservation.model.PricePojo;
import at.fhv.roomix.controller.reservation.model.ReservationUnitPojo;
import at.fhv.roomix.controller.reservation.model.RoomCategoryPojo;
import at.fhv.roomix.ui.common.LabelBuilder;
import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.common.validator.DateValidator;
import at.fhv.roomix.ui.dataprovider.ReservationUnitProvider;
import at.fhv.roomix.ui.view.reservation.edit.ReservationEditScope;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.utils.itemlist.ListTransformation;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
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

    private final ReservationUnitProvider provider;
    private final ObservableList<CategoryItemViewModel> roomCategories;

    @InjectResourceBundle
    private ResourceBundle resourceBundle;
    @InjectScope
    private ReservationEditScope scope;

    private StringProperty duration = new SimpleStringProperty();
    private CompositeValidator formValidator = new CompositeValidator();
    private StringProperty arrivalTime = new SimpleStringProperty();
    private ObservableList<PacketsItemViewModel<ArrangementPojo>> arrangementList;
    private ObservableList<XYChart.Series<Number, String>> availableRooms = FXCollections.observableArrayList();
    private StringProperty currCategoryPrice = new SimpleStringProperty();
    private StringProperty amountAsStringProperty = new SimpleStringProperty();

    private Validator arrivalDateValidator;
    private Validator departureDateValidator;
    private Validator arrivalTimeValidator;
    private Validator amountValidator;
    private Validator categoriesValidator;

    public UnitViewModel() {
        provider = new ReservationUnitProvider();

//        ListTransformation<RoomCategoryPojo, CategoryItemViewModel> transRoomCategory
//                = new ListTransformation<>(provider.getPossibleCategories(), CategoryItemViewModel::new);
//        roomCategories = transRoomCategory.getTargetList();
        roomCategories = FXCollections.observableArrayList();

        ListTransformation<ArrangementPojo, PacketsItemViewModel<ArrangementPojo>> transArticle
                = new ListTransformation<>(provider.getPossibleArrangements(), pojo -> new PacketsItemViewModel<>(pojo, LabelBuilder.getArrangementILabelBuilder()));
        arrangementList = transArticle.getTargetList();
    }

    ObjectProperty<LocalDate> arrivalDateProperty() {
        return pojoWrapper.field("arrivalDate",
                ReservationUnitPojo::getStartDate, ReservationUnitPojo::setStartDate);
    }

    ObjectProperty<LocalDate> departureDateProperty() {
        return pojoWrapper.field("departureDate",
                ReservationUnitPojo::getEndDate, ReservationUnitPojo::setEndDate);
    }

    ObjectProperty<RoomCategoryPojo> categoryProperty() {
        return pojoWrapper.field("roomCategory",
                ReservationUnitPojo::getRoomCategory, ReservationUnitPojo::setRoomCategory);
    }

    ObjectProperty<PricePojo> priceProperty() {
        return pojoWrapper.field("price",
                ReservationUnitPojo::getPrice, ReservationUnitPojo::setPrice);
    }

    ObjectProperty<Collection<ArrangementPojo>> arrangementsProperty() {
        return pojoWrapper.field("arrangments",
                ReservationUnitPojo::getArrangements, ReservationUnitPojo::setArrangements);
    }

    private IntegerProperty amountProperty() {
        return pojoWrapper.field("amount",
                ReservationUnitPojo::getAmount, ReservationUnitPojo::setAmount);
    }

    private ObjectProperty<LocalTime> arrivalTime() {
        return pojoWrapper.field("arrivalTime",
                ReservationUnitPojo::getArrivalTime, ReservationUnitPojo::setArrivalTime);
    }

    StringProperty amountAsStringPropertyProperty() {
        return amountAsStringProperty;
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

        provider.loadCategories(arrivalDateProperty().get(), departureDateProperty().get(), scope.getContractingParty(), this::createVms);
    }

    private void createVms() {
        roomCategories.clear();
        for (RoomCategoryPojo pojo : provider.getPossibleCategories()) {
            roomCategories.add(new CategoryItemViewModel(pojo));
        }
    }

    ValidationStatus getArrivalDateValidator() {
        return arrivalDateValidator.getValidationStatus();
    }

    ValidationStatus getDepartureDateValidator() {
        return departureDateValidator.getValidationStatus();
    }

    ValidationStatus getArrivalTimeValidator() {
        return arrivalTimeValidator.getValidationStatus();
    }

    ValidationStatus getCategoryValidator() {
        return categoriesValidator.getValidationStatus();
    }

    ValidationStatus getAmountValidator() {
        return amountValidator.getValidationStatus();
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


        arrivalDateValidator = new DateValidator(arrivalDateProperty());
        departureDateValidator = new DateValidator(departureDateProperty());
        arrivalTimeValidator = new FunctionBasedValidator<>(
                arrivalTime(), Objects::nonNull, ValidationMessage.error(""));
        amountValidator = new FunctionBasedValidator<>(
                amountProperty(), (i) -> i.intValue() <= 0, ValidationMessage.error("")
        );
        categoriesValidator = new FunctionBasedValidator<>(
                categoryProperty(), Objects::nonNull, ValidationMessage.error(""));

        formValidator = new CompositeValidator(
                arrivalDateValidator,
                departureDateValidator,
                arrivalTimeValidator,
                amountValidator,
                categoriesValidator
        );

        isValid = formValidator.getValidationStatus().validProperty();

        categoryProperty().addListener(((observable, oldValue, newValue) -> {
            availableRooms.clear();
            currCategoryPrice.setValue("?");
            if (newValue == null) return;
            XYChart.Series<Number, String> series = new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>(newValue.getFree(),
                    StringResourceResolver.getStaticResolve(resourceBundle, "reservation.edit.unit.freerooms")));
            series.getData().add(new XYChart.Data<>(newValue.getQuota(),
                    StringResourceResolver.getStaticResolve(resourceBundle, "reservation.edit.unit.quotarooms")));
            series.getData().add(new XYChart.Data<>(newValue.getOccupied(),
                    StringResourceResolver.getStaticResolve(resourceBundle, "reservation.edit.unit.occupiedrooms")));
            series.getData().add(new XYChart.Data<>(newValue.getUnconfirmedReservation(),
                    StringResourceResolver.getStaticResolve(resourceBundle, "reservation.edit.unit.unconfirmedrooms")));
            availableRooms.add(series);
            currCategoryPrice.setValue(Float.toString(newValue.getPricePerDay() / 100F) + " €");
        }));

        amountAsStringProperty.addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                amountProperty().setValue(1);
                return;
            }
            String trimmedString = newValue.trim();
            if (trimmedString.isEmpty()) {
                amountProperty().setValue(1);
                return;
            }
            if (!trimmedString.matches("^(\\d)+$")) {
                amountProperty().setValue(1);
                return;
            }
            int i = Integer.parseInt(trimmedString);
            amountProperty().setValue(i);
        }));
    }


    @Override
    protected void afterSubscribe(boolean isNew) {
        roomCategories.clear();
        arrangementList.clear();
        provider.clear();

        provider.loadArrangements(() -> {
            if (arrangementsProperty().get() == null) return;
            for (ArrangementPojo pojo : arrangementsProperty().get()) {
                for (PacketsItemViewModel<ArrangementPojo> model : arrangementList) {
                    if (model.getPojo().getId() != pojo.getId()) continue;
                    model.checkedProperty().setValue(true);
                }
            }
        });

        if (amountProperty().get() <= 0) {
            amountProperty().setValue(1);
        }
    }

    ReadOnlyBooleanProperty getContactInLoad() {
        return provider.inLoadCategoriesProperty();
    }

    ReadOnlyBooleanProperty getArrangementsInLoad() {
        return provider.inLoadArrangementsProperty();
    }

    ObservableList<PacketsItemViewModel<ArrangementPojo>> getArrangementList() {
        return arrangementList;
    }

    ObservableList<CategoryItemViewModel> getRoomCategories() {
        return roomCategories;
    }

    ObservableList<XYChart.Series<Number, String>> getAvailableRooms() {
        return availableRooms;
    }

    ReadOnlyStringProperty currCategoryPriceProperty() {
        return currCategoryPrice;
    }

    void onCommit() {
        HashSet<ArrangementPojo> arrangementPojos = new HashSet<>();
        for (PacketsItemViewModel<ArrangementPojo> viewModel : arrangementList) {
            if (!viewModel.isChecked()) continue;
            arrangementPojos.add(viewModel.getPojo());
        }
        arrangementsProperty().setValue(arrangementPojos);
        commit();
        provider.calculatePrice((price) -> {
            priceProperty().setValue(price);
            commit();
        }, currModel.get(), scope.getContractingParty());
    }
}
