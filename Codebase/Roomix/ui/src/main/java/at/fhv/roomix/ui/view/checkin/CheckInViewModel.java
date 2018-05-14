package at.fhv.roomix.ui.view.checkin;

import at.fhv.roomix.ui.common.AbstractMasterEditScope;
import at.fhv.roomix.ui.common.IErrorCall;
import at.fhv.roomix.ui.view.reservation.scope.EDataProvider;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

@ScopeProvider(scopes = ReservationViewScope.class)
public class CheckInViewModel implements ViewModel {

    private BooleanProperty contentViewVisible = new SimpleBooleanProperty();
    private BooleanProperty editViewVisible = new SimpleBooleanProperty();

    @InjectScope
    private ReservationViewScope scope;

    BooleanProperty contentViewVisibleProperty() {
        return contentViewVisible;
    }

    public void initialize(){
        scope.init(EDataProvider.CheckInProvider);
        scope.subscribe(AbstractMasterEditScope.commandContentView, (key, payload) -> showContentView());
        scope.subscribe(AbstractMasterEditScope.commandEditView, (key, payload) -> showEditView());
        scope.subscribe(ReservationViewScope.commandInternalError, (key, payload) -> onInternalError(payload));
        scope.subscribe(ReservationViewScope.commandSaveUpdateError, (key, payload) -> onSaveUpdateError(payload));
        showContentView();
    }

    private void showContentView(){
        contentViewVisible.setValue(true);
        editViewVisible.setValue(false);
        scope.getSearchQueryProperty().setValue(null);
        scope.getSearchQueryProperty().setValue("");
    }

    private void showEditView(){
        contentViewVisible.setValue(false);
        editViewVisible.setValue(true);
    }

    BooleanProperty editViewVisibleProperty() {
        return editViewVisible;
    }

    private void onInternalError(Object[] payload){
        if (internalErrorCall == null) return;
        Error error = null;
        try {
            error = (Error) payload[0];
        } catch (ClassCastException ignore) { }
        internalErrorCall.errorOccurred(error);
    }

    private void onSaveUpdateError(Object[] payload){
        if (saveUpdateErrorCall == null) return;
        Error error = null;
        try {
            error = (Error) payload[0];
        } catch (ClassCastException ignore) { }
        saveUpdateErrorCall.errorOccurred(error);
    }

    private IErrorCall internalErrorCall;
    private IErrorCall saveUpdateErrorCall;

    void setInternalErrorCallBack(IErrorCall callBack) {
        internalErrorCall = callBack;
    }

    void setSaveUpdateErrorCallBack(IErrorCall callBack) {
        saveUpdateErrorCall = callBack;
    }
}
