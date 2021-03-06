package at.fhv.roomix.ui.view.contact;

import at.fhv.roomix.ui.common.AbstractPage;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact
 * ContactPage
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactPage extends AbstractPage {
    private static final String DEFAULT_TAG = "common.Contact";
    private static final String DEFAULT_GLYPH = "GROUP";

    public ContactPage() {
        super(new SwitchablePage(DEFAULT_TAG, DEFAULT_GLYPH, ContactView.class));
        page.tagProperty().setValue(DEFAULT_TAG);
    }
}
