package at.fhv.roomix.controller.implement.adapter;

import at.fhv.roomix.domain.implement.IReservation;
import at.fhv.roomix.domain.implement.IReservationUnit;
import at.fhv.roomix.domain.reservation.Reservation;
import at.fhv.roomix.domain.reservation.ReservationUnit;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Roomix
 * at.fhv.roomix.controller.implement.adapter
 * ${FILE_NAME}
 * 19/05/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class ReservationAdapter implements IReservation {

    private final Reservation reservation;
    private List<IReservationUnit> unitAdapters;

    public ReservationAdapter(Reservation reservation) {
        if (reservation == null) throw new IllegalArgumentException();
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public int getReservationID() {
        return reservation.getId();
    }

    @Override
    public String getFirstName() {
        return reservation.getContractingParty().getContact().getFirstName();
    }

    @Override
    public String getLastName() {
        return reservation.getContractingParty().getContact().getLastName();
    }

    @Override
    public String getCompanyName() {
        return reservation.getContractingParty().getContact().getCompanyName();
    }

    @Override
    public String getReservationStatus() {
        return reservation.getStatus().toString();
    }

    @Override
    public String getReservationComment() {
        return reservation.getComment();
    }

    @Override
    public Date getReservationStartDate() {
        Optional<ReservationUnit> optionalUnit = reservation.getUnits()
                .stream().min(Comparator.comparing(ReservationUnit::getStartDate));

        if (!optionalUnit.isPresent()) throw new IllegalStateException("Reservation has no Unit");
        return Date.from(optionalUnit.get().getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Date getReservationEndDate() {
        Optional<ReservationUnit> optionalUnit = reservation.getUnits()
                .stream().max(Comparator.comparing(ReservationUnit::getEndDate));

        if (!optionalUnit.isPresent()) throw new IllegalStateException("Reservation has no Unit");
        return Date.from(optionalUnit.get().getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public List<IReservationUnit> getReservationUnits() {
        if (unitAdapters != null) return unitAdapters;
        unitAdapters = reservation.getUnits().stream().map(ReservationUnitAdapter::new).collect(Collectors.toList());
        return unitAdapters;
    }
}
