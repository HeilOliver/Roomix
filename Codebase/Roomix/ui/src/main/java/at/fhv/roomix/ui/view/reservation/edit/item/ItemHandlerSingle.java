package at.fhv.roomix.ui.view.reservation.edit.item;

import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.FxmlView;
import javafx.beans.property.ObjectProperty;
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
    private ObjectProperty<ItemControlViewModel<T>> item = new SimpleObjectProperty<>();

    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    ItemHandlerSingle(Class<? extends ViewType> viewType, IContentBuilder<T> contentBuilder,
                      ObjectProperty<ItemControlViewModel> currentSelection, ObjectProperty<Parent> currentView,
                      Supplier<T> emptyTypeSupplier) {
        super(viewType, contentBuilder, currentSelection, currentView, emptyTypeSupplier);
        item.addListener((observable, oldValue, newValue) ->
                addAble.setValue(item.get() == null));
    }

    @Override
    public void add() {
        if (item.get() != null) return;
        ItemControlViewModel<T> itemControl =
                new ItemControlViewModel<>(this, contentBuilder);
        item.setValue(itemControl);
        select(itemControl);
    }

    @Override
    public void delete(ItemControlViewModel<T> me) {
        item.setValue(null);
        if (me.equals(currentSelectionProperty().get())) {
            select(null);
        }
    }

    @Override
    public void clear() {
        if (item.get() != null)
            item.get().dispose();
        item.setValue(null);
    }

    public ObjectProperty<ItemControlViewModel<T>> currentItem() {
        return item;
    }

    public T getObject() {
        ItemControlViewModel<T> control = currentItem().get();
        if (control == null) return null;
        return control.getPojo();
    }

    public void setObject(T object) {
        if (item.get() != null)
            throw new IllegalStateException("Item is already set");

        ItemControlViewModel<T> itemControl =
                new ItemControlViewModel<>(this, contentBuilder);
        if (object != null)
            itemControl.setPojo(object);
        item.setValue(itemControl);
    }
}
