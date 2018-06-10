package at.fhv.roomix.ui.implement;

import at.fhv.roomix.ui.common.AbstractPage;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;

public class RoomAssignmentPage extends AbstractPage {
    private static final String DEFAULT_TAG = "common.RoomAssignment";
    private static final String DEFAULT_GLYPH = "BED";

    public RoomAssignmentPage() {
        super(new SwitchablePage(DEFAULT_TAG, DEFAULT_GLYPH, ImplementView.class));
    }
}
