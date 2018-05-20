package at.fhv.roomix.domain.implement;

import java.util.Date;
import java.util.List;

public interface IReservation {
    int getReservationID();

    String getFirstName();

    String getLastName();

    String getCompanyName();

    String getReservationStatus();

    String getReservationComment();

    Date getReservationStartDate();

    Date getReservationEndDate();

    List<IReservationUnit> getReservationUnits();
}
