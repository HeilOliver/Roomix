package at.fhv.roomix.ui.view.reservation.edit.item;

import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.Scope;
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
    private final Scope scope;
    private SubscribeAbleViewModel<T> viewModel;
    private final Object lock = new Object();
    private boolean init = false;

    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    DetailViewTuple(Class<? extends ViewType> type) {
        this(type, null);
    }

    public <ViewType extends FxmlView<? extends SubscribeAbleViewModel<T>>>
    DetailViewTuple(Class<? extends ViewType> type, Scope providedScope) {
        this.type = type;
        scope = providedScope;
    }

    @SuppressWarnings("unchecked")
    private void create() {
        if (init) return;
        init = true;

        FluentViewLoader.FxmlViewStep<? extends FxmlView<?
                extends SubscribeAbleViewModel<T>>, ?
                extends SubscribeAbleViewModel<T>> viewStep = FluentViewLoader.fxmlView(type);

        if (scope != null)
            viewStep.providedScopes(scope);

        ViewTuple<? extends FxmlView<? extends SubscribeAbleViewModel<T>>,
                        ? extends SubscribeAbleViewModel<T>> viewTuple = viewStep.load();

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
