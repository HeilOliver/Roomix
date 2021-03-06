package at.fhv.roomix.ui.view.reservation.scope;

import at.fhv.roomix.ui.dataprovider.AbstractProvider;
import at.fhv.roomix.ui.dataprovider.AbstractSearchEditProvider;
import at.fhv.roomix.ui.dataprovider.CheckInProvider;
import at.fhv.roomix.ui.dataprovider.ReservationProvider;

public enum EDataProvider {
    ReservationProvider(new ReservationProvider()), CheckInProvider(new CheckInProvider());

    private AbstractSearchEditProvider provider;

    private  EDataProvider(AbstractSearchEditProvider provider){
        this.provider = provider;
    }

    public AbstractSearchEditProvider get(){
        return provider;
    }
}
