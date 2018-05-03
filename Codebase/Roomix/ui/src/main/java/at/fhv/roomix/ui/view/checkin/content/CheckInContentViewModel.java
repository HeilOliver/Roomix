package at.fhv.roomix.ui.view.checkin.content;

import at.fhv.roomix.ui.view.checkin.scope.CheckInScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CheckInContentViewModel implements ViewModel {

    private BooleanProperty detailOpenProperty = new SimpleBooleanProperty();

    @InjectScope
    private CheckInScope checkInScope;

    public void initialize() {
        checkInScope.selectedPojoProperty().addListener(((observable, oldValue, newValue) -> {
            detailOpenProperty.setValue(newValue != null);
        }));
    }

    public BooleanProperty detailOpenPropertyProperty() {
        return detailOpenProperty;
    }
}
