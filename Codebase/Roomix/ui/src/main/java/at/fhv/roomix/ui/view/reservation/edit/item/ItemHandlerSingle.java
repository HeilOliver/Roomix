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
    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    ItemHandlerSingle(Class<? extends ViewType> viewType, IContentBuilder<T> contentBuilder,
                      ObjectProperty<ItemControlViewModel> currentSelection, ObjectProperty<Parent> currentView,
                      Supplier<T> emptyTypeSupplier) {
        super(viewType, contentBuilder, currentSelection, currentView, emptyTypeSupplier);
        item.addListener((observable, oldValue, newValue) ->
                addAble.setValue(item.get() == null));
    }
    private ObjectProperty<ItemControlViewModel<T>> item = new SimpleObjectProperty<>();

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

    public ObjectProperty<ItemControlViewModel<T>> currentItem() {
        return item;
    }
}
