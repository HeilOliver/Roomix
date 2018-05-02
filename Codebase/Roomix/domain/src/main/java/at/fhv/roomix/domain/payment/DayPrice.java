package at.fhv.roomix.domain.payment;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.domain.payment
 * DayPrice
 * 02/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class DayPrice {
    private LocalDate date;
    private int prize;

    public DayPrice(LocalDate date, int prize) {
        this.date = date;
        this.prize = prize;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPrize() {
        return prize;
    }
}
