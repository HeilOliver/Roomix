package at.fhv.roomix.ui.view.reservation.edit.unit;

import at.fhv.roomix.controller.reservation.model.RoomCategoryPojo;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.controlsfx.control.SegmentedBar;

import java.util.ArrayList;
import java.util.List;
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

    @InjectResourceBundle
    private ResourceBundle resourceBundle;

    private List<SegmentedBar.Segment> segments = new ArrayList<>();

    private final RoomCategoryPojo pojo;

    public CategoryItemViewModel(RoomCategoryPojo pojo) {
        this.pojo = pojo;

        StringBuilder sb = new StringBuilder();
        sb.append(pojo.getDiscription());
        sb.append("Free:");
        sb.append(pojo.getFree());
        sb.append(" Occupied:");
        sb.append(pojo.getOccupied());
        sb.append(" Unconfirmed:");
        sb.append(pojo.getUnconfirmedReservation());
        if (pojo.getQuota() > 0) {
            sb.append(" Quota:");
            sb.append(pojo.getQuota());
        }
        content.setValue(sb.toString());
    }

    public void initialize() {

    }

    List<SegmentedBar.Segment> getSegments() {
        return segments;
    }

    private StringProperty content = new SimpleStringProperty();

    public StringProperty contentProperty() {
        return content;
    }
}
