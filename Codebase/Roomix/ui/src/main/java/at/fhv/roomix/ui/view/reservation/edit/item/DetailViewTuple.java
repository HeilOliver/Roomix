package at.fhv.roomix.ui.view.reservation.edit.item;

import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.Parent;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit
 * DetailViewTuple
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class DetailViewTuple<T> {
    private final Class<? extends FxmlView<? extends SubscribeAbleViewModel<T>>> type;
    private Parent parent;
    private SubscribeAbleViewModel<T> viewModel;
    private final Object lock = new Object();
    private boolean init = false;

    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    DetailViewTuple(Class<? extends ViewType> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    private void create() {
        if (init) return;
        init = true;
        ViewTuple<? extends FxmlView<? extends SubscribeAbleViewModel<T>>,
                        ? extends SubscribeAbleViewModel<T>> viewTuple = FluentViewLoader.fxmlView(type).load();

        parent = viewTuple.getView();
        viewModel = viewTuple.getViewModel();
    }

    Parent getParent() {
        if (!init) synchronized (lock) {create();}
        return parent;
    }

    public SubscribeAbleViewModel<T> getViewModel() {
        if (!init) synchronized (lock) {create();}
        return viewModel;
    }

    public Class<? extends FxmlView<? extends SubscribeAbleViewModel<T>>> getType() {
        return type;
    }

    boolean isInit() {
        return init;
    }
}
