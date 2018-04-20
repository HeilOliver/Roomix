package at.fhv.roomix.controller.reservation.model;

import java.time.LocalDate;

public class OccupationPojo {
    private LocalDate startDate;
    private LocalDate endDate;


    private status status;

    private enum status{
        FREE,
        RESERVATED,
        OCCUPIED,
        RESERVEDNOTCONFERMED;
    }

    public OccupationPojo.status getStatus() {
        return status;
    }

    public void setStatus(OccupationPojo.status status) {
        this.status = status;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }









}
