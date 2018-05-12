package at.fhv.roomix.ui.view.reservation.edit.option;

import at.fhv.roomix.controller.model.PricePojo;
import at.fhv.roomix.controller.model.ReservationOptionPojo;
import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    // region Validation
    private Validator descriptionValidator;
    private Validator dueDateValidator;
    private Validator priceValidator;

    ReadOnlyBooleanProperty isCommitAble() {
        return pojoWrapper.dirtyProperty();
    }

    StringProperty downPriceProperty() {
        return downPrice;
    }

    StringProperty descriptionProperty() {
        return pojoWrapper.field("description",
                ReservationOptionPojo::getOptionDescription, ReservationOptionPojo::setOptionDescription);
    }

    ObjectProperty<LocalDate> dueDateProperty() {
        return pojoWrapper.field("dueDate",
                ReservationOptionPojo::getOptionDueDate, ReservationOptionPojo::setOptionDueDate);
    }

    private ObjectProperty<PricePojo> priceProperty() {
        return pojoWrapper.field("price",
                ReservationOptionPojo::getOptionFee, ReservationOptionPojo::setOptionFee);
    }

    ValidationStatus descriptionValidation() {
        return descriptionValidator.getValidationStatus();
    }

    ValidationStatus dueDateValidation() {
        return dueDateValidator.getValidationStatus();
    }

    ValidationStatus priceValidation() {
        return priceValidator.getValidationStatus();
    }

    private void initValidation() {
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

        priceValidator = new FunctionBasedValidator<>(
                priceProperty(),
                price -> price != null && price.getPrice() > 0,
                ValidationMessage.error(StringResourceResolver.getStaticResolve(resourceBundle,
                        "reservation.edit.option.priceinvalid"))
        );

        CompositeValidator compositeValidator = new CompositeValidator(
                descriptionValidator,
                dueDateValidator,
                priceValidator
        );

        isValid = compositeValidator.getValidationStatus().validProperty();
    }
    // endregion

    public void initialize() {
        initValidation();

        downPriceProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                PricePojo pricePojo = new PricePojo();
                float price = Float.parseFloat(newValue);
                pricePojo.setPrice(Math.round(price * 100));
                priceProperty().setValue(pricePojo);
            } catch (NumberFormatException e) {
                priceProperty().setValue(null);
            }
        }));
    }

    @Override
    protected void afterSubscribe(boolean isNew) {
        if (isNew) {
            downPrice.setValue("");
        } else {
            PricePojo pricePojo = priceProperty().get();

            if (pricePojo == null) {
                downPrice.setValue("");
                return;
            }

            int price = pricePojo.getPrice();
            Float commaPrice = (float) price / 100;
            downPrice.setValue(commaPrice.toString());
        }
    }

    void commitChange() {
        commit();
    }

}
