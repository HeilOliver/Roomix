package at.fhv.roomix.ui.view.reservation.edit.unit;

import at.fhv.roomix.controller.model.RoomCategoryPojo;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.unit
 * CategoryItemViewModel
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CategoryItemViewModel implements ViewModel {

    private final RoomCategoryPojo pojo;

    @InjectResourceBundle
    private ResourceBundle resourceBundle;

    private StringProperty content = new SimpleStringProperty();

    public CategoryItemViewModel(RoomCategoryPojo pojo) {
        this.pojo = pojo;

        StringBuilder sb = new StringBuilder();
        sb.append(pojo.getDescription());
        // TODO: pass correct pojo and collect information to string
        //sb.append(pojo.getUnconfirmedReservation());
//        if (pojo.getQuota() > 0) {
//            sb.append(" Quota:");
//            sb.append(pojo.getQuota());
//        }
        content.setValue(sb.toString());
    }

    public void initialize() {

    }

    public RoomCategoryPojo getPojo() {
        return pojo;
    }

    public StringProperty contentProperty() {
        return content;
    }
}
