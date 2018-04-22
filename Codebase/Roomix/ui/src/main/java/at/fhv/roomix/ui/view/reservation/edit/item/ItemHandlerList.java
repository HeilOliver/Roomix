package at.fhv.roomix.ui.view.reservation.edit.item;

import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit
 * ItemHandlerList
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ItemHandlerList<T> extends ItemHandler<T> {
    private final int maxSize;
    private ObservableList<ItemControlViewModel> items = FXCollections.observableArrayList();

    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    ItemHandlerList(Class<? extends ViewType> viewType, IContentBuilder<T> contentBuilder,
                    ObjectProperty<ItemControlViewModel> currentSelection, ObjectProperty<Parent> currentView, Supplier<T> emptyTypeSupplier) {
        this(viewType,contentBuilder, currentSelection, currentView, emptyTypeSupplier, Integer.MAX_VALUE);
    }

    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    ItemHandlerList(Class<? extends ViewType> viewType, IContentBuilder<T> contentBuilder,
                    ObjectProperty<ItemControlViewModel> currentSelection, ObjectProperty<Parent> currentView,
                    Supplier<T> emptyTypeSupplier, Scope viewScope) {
        super(viewType, contentBuilder, currentSelection, currentView, emptyTypeSupplier, viewScope);
        this.maxSize = Integer.MAX_VALUE;
    }

    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    ItemHandlerList(Class<? extends ViewType> viewType, IContentBuilder<T> contentBuilder,
                    ObjectProperty<ItemControlViewModel> currentSelection, ObjectProperty<Parent> currentView,
                    Supplier<T> emptyTypeSupplier, int maxSize) {
        super(viewType, contentBuilder, currentSelection, currentView, emptyTypeSupplier);
        this.maxSize = maxSize;

        items.addListener((ListChangeListener<? super ItemControlViewModel>)
                c -> addAble.setValue(items.size() <= maxSize));
    }

    @Override
    public void add() {
        if (items.size() >= maxSize)
            throw new IllegalStateException("To much items in this list");

        ItemControlViewModel<T> model =
                new ItemControlViewModel<>(this, contentBuilder);
        items.add(model);
        select(model);
    }

    @Override
    public void delete(ItemControlViewModel<T> me){
        items.remove(me);
        if (me.equals(currentSelectionProperty().get())) {
            select(null);
        }
    }

    @Override
    public void clear() {
        items.forEach(ItemControlViewModel::dispose);
        items.clear();
    }

    public void setObjects(Collection<T> objects) {
        if (items.size() >= maxSize)
            throw new IllegalStateException("To much items in this list");

        for (T object : objects) {
            ItemControlViewModel<T> model =
                    new ItemControlViewModel<>(this, contentBuilder);
            model.setPojo(object);
            items.add(model);
        }
    }

    public Collection<T> getObjects() {
        HashSet<T> set = new HashSet<>();
        for (@SuppressWarnings("unchecked")
                ItemControlViewModel<T> item : items) {
            set.add(item.getPojo());
        }
        return set;
    }

    public ObservableList<ItemControlViewModel> currentItems() {
        return items;
    }

}


