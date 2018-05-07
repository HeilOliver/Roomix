package at.fhv.roomix.ui.view.checkin;

import at.fhv.roomix.ui.common.AbstractPage;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;

public class CheckInPage extends AbstractPage {
    private static final String DEFAULT_TAG = "common.CheckIn";
    private static final String DEFAULT_GLYPH = "CHECK_CIRCLE";

    public CheckInPage() {
        super(new SwitchablePage(DEFAULT_TAG, DEFAULT_GLYPH, CheckInView.class));
    }
}
