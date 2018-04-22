package at.fhv.roomix.ui.view.reservation.edit.option;

import at.fhv.roomix.controller.reservation.model.ReservationOptionPojo;
import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.option
 * OptionViewModel
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class OptionViewModel extends SubscribeAbleViewModel<ReservationOptionPojo> {

    @InjectResourceBundle
    private ResourceBundle resourceBundle;
    private StringProperty downPrice = new SimpleStringProperty();

    private Validator descriptionValidator;
    private Validator dueDateValidator;
    private CompositeValidator compositePriceValidator;

    ReadOnlyBooleanProperty isCommitAble() {
        return pojoWrapper.dirtyProperty();
    }

    StringProperty descriptionProperty() {
        return pojoWrapper.field("description",
                ReservationOptionPojo::getOptionDescription, ReservationOptionPojo::setOptionDescription);
    }

    StringProperty downPriceProperty() {
        return downPrice;
    }

    private IntegerProperty priceProperty() {
        return pojoWrapper.field("price",
                ReservationOptionPojo::getOptionFee, ReservationOptionPojo::setOptionFee);
    }

    ObjectProperty<LocalDate> dueDateProperty() {
        return pojoWrapper.field("dueDate",
                ReservationOptionPojo::getOptionDueDate, ReservationOptionPojo::setOptionDueDate);
    }

    ValidationStatus descriptionValidation() {
        return descriptionValidator.getValidationStatus();
    }

    ValidationStatus dueDateValidation() {
        return dueDateValidator.getValidationStatus();
    }

    ValidationStatus priceValidation() {
        return compositePriceValidator.getValidationStatus();
    }

    public void initialize() {
        descriptionValidator = new FunctionBasedValidator<>(
                descriptionProperty(),
                string -> string != null && !string.trim().isEmpty(),
                ValidationMessage.error(StringResourceResolver.getStaticResolve(resourceBundle,
                        "reservation.edit.option.descriptionEmpty")));

        dueDateValidator = new FunctionBasedValidator<>(
                dueDateProperty(),
                date -> date != null && !date.isBefore(LocalDate.now()),
                ValidationMessage.error(StringResourceResolver.getStaticResolve(resourceBundle,
                        "reservation.edit.option.dueDateBeforeNow")));

        Validator priceNullEmptyValidator = new FunctionBasedValidator<>(
                priceProperty(),
                price -> price.intValue() <= 0,
                ValidationMessage.error(StringResourceResolver.getStaticResolve(resourceBundle,
                        "reservation.edit.option.priceinvalid"))
        );

        Validator priceInvalidNumberValidator = new FunctionBasedValidator<>(
                downPriceProperty(),
                string -> string != null && !string.trim().isEmpty(),
                ValidationMessage.error(StringResourceResolver.getStaticResolve(resourceBundle,
                        "reservation.edit.option.priceinvalid"))
        );

        compositePriceValidator = new CompositeValidator(
                priceInvalidNumberValidator,
                priceNullEmptyValidator
        );

        downPriceProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                int i = Integer.parseInt(newValue);
                priceProperty().setValue(i);
            } catch (NumberFormatException ignore){
            }
        }));

        isValid = compositePriceValidator.getValidationStatus().validProperty();
    }

    @Override
    protected void afterSubscribe(boolean isNew) {
        Integer i = priceProperty().get();
        downPriceProperty().setValue(i.toString());
    }

    void commitChange() {
        commit();
    }

}
