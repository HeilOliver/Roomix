package at.fhv.roomix.ui.common;

import at.fhv.roomix.ui.view.main.models.SwitchablePage;

/**
 * Roomix
 * at.fhv.roomix.ui.common
 * AbstractPage
 * 14/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public abstract class AbstractPage {
    protected final SwitchablePage page;

    protected AbstractPage(SwitchablePage page) {
        this.page = page;
    }

    public SwitchablePage getPage() {
        return page;
    }
}
