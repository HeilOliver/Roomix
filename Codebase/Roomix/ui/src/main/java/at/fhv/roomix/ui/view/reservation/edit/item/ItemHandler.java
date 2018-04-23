package at.fhv.roomix.ui.view.reservation.edit.item;

import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.*;
import javafx.scene.Parent;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.item
 * ItemHandler
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public abstract class ItemHandler<T> {
    private final DetailViewTuple<T> viewTuple;
    protected final IContentBuilder<T> contentBuilder;
    private final ObjectProperty<ItemControlViewModel> currentSelection;
    private final ObjectProperty<Parent> currentView;
    protected BooleanProperty addAble = new SimpleBooleanProperty();


    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    ItemHandler(Class<? extends ViewType> viewType, IContentBuilder<T> contentBuilder,
                ObjectProperty<ItemControlViewModel> currentSelection,
                ObjectProperty<Parent> currentView, Supplier<T> emptyPojoSupplier) {
        this(viewType, contentBuilder, currentSelection, currentView, emptyPojoSupplier, null);
    }

    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    ItemHandler(Class<? extends ViewType> viewType, IContentBuilder<T> contentBuilder,
                ObjectProperty<ItemControlViewModel> currentSelection,
                ObjectProperty<Parent> currentView, Supplier<T> emptyPojoSupplier, Scope providedScope) {
        if (providedScope == null) {
            viewTuple = new DetailViewTuple<>(viewType);
        } else {
            viewTuple = new DetailViewTuple<>(viewType, providedScope);
        }
        this.emptyPojoSupplier = emptyPojoSupplier;
        this.contentBuilder = contentBuilder;
        this.currentSelection = currentSelection;
        this.currentView = currentView;

        currentView.addListener((observable, oldValue, newValue) -> {
            if (!viewTuple.isInit()) return;
            if (newValue == null) return;
            if (newValue.equals(viewTuple.getParent())) return;
            if (oldValue == null) return;
            if (!oldValue.equals(viewTuple.getParent())) return;
            viewTuple.getViewModel().unSubscribe();
        });

        addAble.setValue(true);
    }

    public abstract void add();

    public abstract void delete(ItemControlViewModel<T> me);

    private Supplier<T> emptyPojoSupplier;

    void select(ItemControlViewModel<T> me){
        currentSelection.setValue(me);
        if (me == null) {
            currentView.setValue(null);
            return;
        }
        currentView.setValue(viewTuple.getParent());
        viewTuple.getViewModel().subscribe(me.currentPojoProperty(),
                emptyPojoSupplier, me.isValidProperty());
    }

    public abstract void clear();

    public ObjectProperty<ItemControlViewModel> currentSelectionProperty() {
        return currentSelection;
    }

    public ReadOnlyBooleanProperty isAddAble() {
        return addAble;
    }
}
