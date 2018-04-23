package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.common.exceptions.FaultException;
import at.fhv.roomix.ui.common.ICallable;
import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.common.ISearchAble;
import at.fhv.roomix.ui.common.validator.IUpdateAble;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * AbstractSearchEditProvider
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public abstract class AbstractSearchEditProvider<T> extends SearchProvider<T> {
    private IUpdateAble<T> updateProvider;

    public AbstractSearchEditProvider(ISearchAble<T> searchProvider, IUpdateAble<T> updateProvider) {
        super(searchProvider);
        this.updateProvider = updateProvider;
    }

    public void saveOrUpdate(T pojo, IErrorCall onError, ICallable onSuccess) {
        submit(() -> {
            try {
                updateProvider.update(pojo);
                reSearch();
                Platform.runLater(onSuccess::call);
            } catch (FaultException e) {
                Platform.runLater(() -> onError.errorOccurred(new Error(e.getMessage())));
            }
        });
    }
}
