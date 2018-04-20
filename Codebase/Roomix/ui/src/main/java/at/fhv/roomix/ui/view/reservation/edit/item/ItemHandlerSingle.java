package at.fhv.roomix.ui.view.reservation.edit.item;

import at.fhv.roomix.ui.view.reservation.edit.ISubscribeAbleViewModel;
import de.saxsys.mvvmfx.FxmlView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.item
 * ItemHandlerSingle
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ItemHandlerSingle<T> extends ItemHandler<T> {
    private final Supplier<T> emptyTypeSupplier;

    public <ViewType extends FxmlView<? extends ISubscribeAbleViewModel<T>>>
    ItemHandlerSingle(Class<? extends ViewType> viewType, IContentBuilder<T> contentBuilder,
                      ObjectProperty<ItemControlViewModel> currentSelection, ObjectProperty<Parent> currentView,
                      Supplier<T> emptyTypeSupplier) {
        super(viewType, contentBuilder, currentSelection, currentView);
        this.emptyTypeSupplier = emptyTypeSupplier;

        item.addListener((observable, oldValue, newValue) ->
                addAble.setValue(item.get() == null));
    }
    private ObjectProperty<ItemControlViewModel<T>> item = new SimpleObjectProperty<>();

    @Override
    public void add() {
        if (item.get() != null) return;
        ItemControlViewModel<T> itemControl =
                new ItemControlViewModel<>(this, contentBuilder);
        itemControl.currentPojoProperty().setValue(emptyTypeSupplier.get());
        item.setValue(itemControl);
    }

    @Override
    public void delete(ItemControlViewModel<T> me) {
        item.setValue(null);
    }

    public ObjectProperty<ItemControlViewModel<T>> currentItem() {
        return item;
    }
}
