package at.fhv.roomix.ui.view.reservation.edit.item;


import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;


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

    public ItemControlViewModel(ItemHandler<T> scope, IContentBuilder<T> builder) {
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

    void onDelete() {
        scope.delete(this);
    }

    public T getPojo() {
        return currentPojo.get();
    }

    public ObjectProperty<T> currentPojoProperty() {
        return currentPojo;
    }

    void selectMe() {
        scope.select(this);
    }

    void dispose() {
        isSelected.unbind();
    }

}
