package at.fhv.roomix.ui.view.reservation.edit.option;

import at.fhv.roomix.controller.reservation.model.ReservationOptionPojo;
import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.view.reservation.edit.ISubscribeAbleViewModel;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
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
public class OptionViewModel implements ISubscribeAbleViewModel<ReservationOptionPojo> {
    private ModelWrapper<ReservationOptionPojo> pojoWrapper = new ModelWrapper<>();

    @InjectResourceBundle
    private ResourceBundle resourceBundle;

    void commitChange() {
        pojoWrapper.commit();
    }

    private Validator descriptionValidator;
    private Validator dueDateValidator;
    private CompositeValidator formValidator = new CompositeValidator();

    ReadOnlyBooleanProperty isCommitAble() {
        return pojoWrapper.dirtyProperty();
    }

    StringProperty descriptionProperty() {
        return pojoWrapper.field("description",
                ReservationOptionPojo::getDescription, ReservationOptionPojo::setDescription);
    }

    ObjectProperty<LocalDate> dueDateProperty() {
        return pojoWrapper.field("dueDate",
                ReservationOptionPojo::getDueDate, ReservationOptionPojo::setDueDate);
    }

    ValidationStatus descriptionValidation() {
        return descriptionValidator.getValidationStatus();
    }

    ValidationStatus dueDateValidation() {
        return dueDateValidator.getValidationStatus();
    }

    public void initialize() {
        descriptionValidator = new FunctionBasedValidator<>(
                descriptionProperty(),
                string -> string != null && !string.trim().isEmpty(),
                ValidationMessage.error(StringResourceResolver.getStaticResolve(resourceBundle,
                        "reservation.edit.option.descriptionEmpty")));

        dueDateValidator = new FunctionBasedValidator<>(
                dueDateProperty(),
                date -> date != null && date.isBefore(LocalDate.now()),
                ValidationMessage.error(StringResourceResolver.getStaticResolve(resourceBundle,
                        "reservation.edit.option.dueDateBeforeNow")));
    }

    @Override
    public void subscribe(ObjectProperty<ReservationOptionPojo> property) {
        pojoWrapper.modelProperty().bindBidirectional(property);
        pojoWrapper.reload();
    }

    @Override
    public void unSubscribe() {
        pojoWrapper.modelProperty().unbind();
    }
}
