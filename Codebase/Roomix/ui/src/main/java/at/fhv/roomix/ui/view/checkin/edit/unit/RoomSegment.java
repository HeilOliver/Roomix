package at.fhv.roomix.ui.view.checkin.edit.unit;

import javafx.scene.control.Label;
import org.controlsfx.control.SegmentedBar;

public class RoomSegment extends SegmentedBar.Segment {

    private String dateRange;

    public RoomSegment(double value, String text, String date) {
        super(value, text);
        dateRange = date;
    }
    public String getDateRange() {
        return dateRange;
    }
}
