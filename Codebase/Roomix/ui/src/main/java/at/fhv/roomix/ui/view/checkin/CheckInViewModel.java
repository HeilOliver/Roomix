package at.fhv.roomix.ui.view.checkin;

import at.fhv.roomix.ui.common.AbstractMasterEditScope;
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
        showContentView();
    }

    private void showContentView(){
        contentViewVisible.setValue(true);
        editViewVisible.setValue(false);
    }

    private void showEditView(){
        contentViewVisible.setValue(false);
        editViewVisible.setValue(true);
    }

    public BooleanProperty editViewVisibleProperty() {
        return editViewVisible;
    }

}
