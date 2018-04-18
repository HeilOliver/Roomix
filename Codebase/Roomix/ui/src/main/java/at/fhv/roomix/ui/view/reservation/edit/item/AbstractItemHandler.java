package at.fhv.roomix.ui.view.reservation.edit.item;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

/**
 * Roomix
 * at.fhv.roomix.ui.common.validator
 * AbstractItemHandler
 * 18/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public abstract class AbstractItemHandler {

    //region Constructor
    public AbstractItemHandler() {
        selectedControl.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                currentDetailView.setValue(null);
                return;
            }
            Parent detailView = newValue.getDetailView();
            currentDetailView.setValue(detailView);
        });
    }
    //endregion

    //region MDRelevant things
    private ObjectProperty<ItemControlViewModel> selectedControl = new SimpleObjectProperty<>();
    private ObjectProperty<Parent> currentDetailView = new SimpleObjectProperty<>();

    public ObjectProperty<ItemControlViewModel> selectedControlProperty() {
        return selectedControl;
    }

    void deleteMe(ItemControlViewModel itemControl) {
        if (itemControl.equals(selectedControl.get()))
            selectedControl.setValue(null);

        deleteItemControl(itemControl);
    }

    abstract protected void deleteItemControl(ItemControlViewModel itemControl);

    void selectMe(ItemControlViewModel itemControl) {
        selectedControl.setValue(itemControl);
    }

    public ObjectProperty<Parent> currentDetailViewProperty() {
        return currentDetailView;
    }

    //endregion
}
