package at.fhv.roomix.ui.view.reservation;

import at.fhv.roomix.ui.common.AbstractPage;
import at.fhv.roomix.ui.view.main.models.SwitchablePage;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation
 * ReservationPage
 * 16/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationPage extends AbstractPage {
    private static final String DEFAULT_TAG = "Reservation";
    private static final String DEFAULT_GLYPH = "FILE_TEXT";

    public ReservationPage() {
        super(new SwitchablePage(DEFAULT_TAG, DEFAULT_GLYPH, ReservationView.class));
    }
}
