package at.fhv.roomix.ui.view.dashboard;

import at.fhv.roomix.ui.common.AbstractPage;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;

/**
 * Roomix
 * at.fhv.roomix.ui.view.dashboard
 * DashBoardPage
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class DashBoardPage extends AbstractPage {
    private static final String DEFAULT_TAG = "DashBoard";
    private static final String DEFAULT_GLYPH = "HOME";

    public DashBoardPage() {
        super(new SwitchablePage(DEFAULT_TAG, DEFAULT_GLYPH, DashBoardView.class));
    }
}
