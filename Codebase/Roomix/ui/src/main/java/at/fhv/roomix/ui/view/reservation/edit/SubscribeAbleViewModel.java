package at.fhv.roomix.ui.view.reservation.edit;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import javafx.beans.property.*;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.comment
 * SubscribeAbleViewModel
 * 20/04/2018 Oliver
 * <p>
 */
public abstract class SubscribeAbleViewModel<T> implements ViewModel {
    protected final ObjectProperty<T> currModel = new SimpleObjectProperty<>();
    protected final ModelWrapper<T> pojoWrapper = new ModelWrapper<>();
    protected ReadOnlyBooleanProperty isValid = new SimpleBooleanProperty();
    protected Supplier<T> emptyPojoSupplier;
    private ObjectProperty<T> currPojoProperty;
    private BooleanProperty currIsValidProperty;

    protected SubscribeAbleViewModel() {
        pojoWrapper.modelProperty().bindBidirectional(currModel);
    }

    public final void subscribe(ObjectProperty<T> property,
                                Supplier<T> emptyPojoSupplier,
                                BooleanProperty valid) {
        if (emptyPojoSupplier == null)
            throw new IllegalStateException("No supplier is given method");
        this.emptyPojoSupplier = emptyPojoSupplier;
        currIsValidProperty = valid;
        currPojoProperty = property;
        boolean isNew = false;
        if (property.get() == null) {
            currModel.setValue(emptyPojoSupplier.get());
            isNew = true;
        } else {
            currModel.setValue(property.get());
        }
        pojoWrapper.reload();
        afterSubscribe(isNew);
    }

    protected void afterSubscribe(boolean isNew) {
    }

    public final void unSubscribe() {
        currPojoProperty = null;
        pojoWrapper.set(null);
        pojoWrapper.reset();
        afterUnSubscribe();
    }

    protected void afterUnSubscribe() {
    }

    protected final void commit() {
        pojoWrapper.commit();
        currIsValidProperty.setValue(isValid.get());

        // TODO change to something correct here.
        // We need to rise an PropertyChangeEvent here in this Property
        // If not we don´t update the UI (ItemControl)
        currPojoProperty.setValue(null);
        currPojoProperty.setValue(pojoWrapper.get());
        afterCommit();
    }

    protected void afterCommit() {
    }

}
