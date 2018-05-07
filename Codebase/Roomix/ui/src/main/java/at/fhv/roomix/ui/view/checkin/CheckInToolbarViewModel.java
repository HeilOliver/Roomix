package at.fhv.roomix.ui.view.checkin;

import at.fhv.roomix.ui.common.AbstractToolbar;
import at.fhv.roomix.ui.view.reservation.scope.EDataProvider;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;

public class CheckInToolbarViewModel extends AbstractToolbar<ReservationViewScope> implements ViewModel {

    @InjectScope
    private ReservationViewScope scope;

    public void initialize() {
        scope.init(EDataProvider.ReservationProvider);
        super.initialize(scope);
    }
}
