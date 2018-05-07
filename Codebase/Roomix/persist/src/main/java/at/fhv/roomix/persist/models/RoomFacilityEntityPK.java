package at.fhv.roomix.persist.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RoomFacilityEntityPK implements Serializable {
    private int room;
    private int facility;

    @Column(name = "Room")
    @Id
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Column(name = "Facility")
    @Id
    public int getFacility() {
        return facility;
    }

    public void setFacility(int facility) {
        this.facility = facility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomFacilityEntityPK that = (RoomFacilityEntityPK) o;
        return room == that.room &&
                facility == that.facility;
    }

    @Override
    public int hashCode() {

        return Objects.hash(room, facility);
    }
}
