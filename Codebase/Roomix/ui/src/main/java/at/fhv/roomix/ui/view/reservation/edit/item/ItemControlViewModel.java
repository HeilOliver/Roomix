package at.fhv.roomix.ui.view.reservation.edit.item;


import at.fhv.roomix.ui.common.StringResourceResolver;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.validation.FunctionBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import de.saxsys.mvvmfx.utils.validation.ValidationStatus;
import javafx.beans.property.*;

import javax.validation.Validation;
import java.util.ResourceBundle;


/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.item
 * ItemControlViewModel
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ItemControlViewModel<T> implements ViewModel {
    private static final String EMPTY = "reservation.item.empty";
    private final ItemHandler<T> scope;

    private final StringProperty contentText = new SimpleStringProperty();
    private final BooleanProperty isSelected = new SimpleBooleanProperty();
    private final ObjectProperty<T> currentPojo = new SimpleObjectProperty<>();

    private BooleanProperty validationStatus = new SimpleBooleanProperty();

    @InjectResourceBundle
    private ResourceBundle resourceBundle;


    ItemControlViewModel(ItemHandler<T> scope, IContentBuilder<T> builder) {
        validationStatus.setValue(true);
        this.scope = scope;

        isSelected.bind(scope.currentSelectionProperty().isEqualTo(this));
        isSelected.addListener(((observable, oldValue, newValue) -> {
            if (!oldValue || newValue) return;
            if (currentPojo.get() == null) onDelete();
        }));

        currentPojo.addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                contentText.setValue(EMPTY);
            } else {
                contentText.setValue(builder.buildString(newValue));
            }
        }));
    }

    ReadOnlyBooleanProperty isSelectedProperty() {
        return isSelected;
    }

    ReadOnlyStringProperty contentTextProperty() {
        return contentText;
    }

    BooleanProperty isValidProperty() {
        return validationStatus;
    }

    ValidationStatus getValidationStatus(){
        FunctionBasedValidator<Boolean> validator = new FunctionBasedValidator<>(
                isValidProperty(),
                bool -> !bool,
                ValidationMessage.error(StringResourceResolver.getStaticResolve(resourceBundle,
                        "reservation.edit.item.invalid")));
        return validator.getValidationStatus();
    }

    void onDelete() {
        scope.delete(this);
    }

    public T getPojo() {
        return currentPojo.get();
    }

    ObjectProperty<T> currentPojoProperty() {
        return currentPojo;
    }

    void selectMe() {
        if (isSelected.get()) return;
        scope.select(this);
    }

    void dispose() {
        isSelected.unbind();
    }

    public void setPojo(T object) {
        currentPojo.setValue(object);
    }
}
