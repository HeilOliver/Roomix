package at.fhv.roomix.ui.view.about;

import at.fhv.roomix.ui.common.AbstractPage;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;

/**
 * Roomix
 * at.fhv.roomix.ui.view.about
 * AboutPage
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class AboutPage extends AbstractPage {
    private static final String DEFAULT_TAG = "common.About";
    private static final String DEFAULT_GLYPH = "INFO";

    public AboutPage() {
        super(new SwitchablePage(DEFAULT_TAG, DEFAULT_GLYPH, AboutView.class));
    }
}
