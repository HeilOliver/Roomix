package at.fhv.roomix.ui.view.reservation.edit.item;

import at.fhv.roomix.ui.common.ISelectionReadWriteAble;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.item
 * ItemControlViewModel
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ItemControlViewModel<T> implements ViewModel {
    private final AbstractItemHandler scope;
    private final StringProperty contentText = new SimpleStringProperty();
    private final BooleanProperty isSelected = new SimpleBooleanProperty();
    private final ObjectProperty<T> currPojo = new SimpleObjectProperty<>();
    private final Parent detailView;

    private static final String IN_EDIT = "reservation.item.inEdit";

    private final ChangeListener<ItemControlViewModel> selectionChangeListener;

    public <V extends FxmlView<VM>, VM extends ViewModel & ISelectionReadWriteAble<T>> ItemControlViewModel(
            AbstractItemHandler scope, Class<V> detailViewType, IContentBuilder<T> builder) {
        this(scope, detailViewType,builder, null);
    }

    public <V extends FxmlView<VM>, VM extends ViewModel & ISelectionReadWriteAble<T>> ItemControlViewModel(
            AbstractItemHandler scope, Class<V> detailViewType, IContentBuilder<T> builder, T injectedPojo) {
        this.scope = scope;

        ViewTuple<V, VM> load = FluentViewLoader.fxmlView(detailViewType).load();
        currPojo.bind(load.getViewModel().getSelectionProperty());
        detailView = load.getView();

        selectionChangeListener = (observable, oldValue, newValue) -> {
            isSelected.setValue(newValue == this);
            if (isSelected.get()) {
                contentText.setValue(IN_EDIT);
            } else {
                contentText.setValue(builder.buildString(currPojo.get()));
            }

            if (oldValue == null || oldValue != this) return;
            if (currPojo.get() == null) onDelete();
        };

        scope.selectedControlProperty().addListener(selectionChangeListener);
        scope.selectMe(this);
    }

    public Parent getDetailView() {
        return detailView;
    }

    ReadOnlyBooleanProperty isSelectedProperty() {
        return isSelected;
    }

    ReadOnlyStringProperty contentTextProperty() {
        return contentText;
    }

    void onDelete() {
        scope.deleteMe(this);
        dispose();
    }

    public T getPojo() {
        return currPojo.get();
    }

    public void dispose() {
        scope.selectedControlProperty().removeListener(selectionChangeListener);
        currPojo.unbind();
    }
}
