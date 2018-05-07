package at.fhv.roomix.persist.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TourGroupMemberEntityPK implements Serializable {
    private int tourGroupId;
    private int tourGroupMember;

    @Column(name = "TourGroupID")
    @Id
    public int getTourGroupId() {
        return tourGroupId;
    }

    public void setTourGroupId(int tourGroupId) {
        this.tourGroupId = tourGroupId;
    }

    @Column(name = "TourGroupMember")
    @Id
    public int getTourGroupMember() {
        return tourGroupMember;
    }

    public void setTourGroupMember(int tourGroupMember) {
        this.tourGroupMember = tourGroupMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourGroupMemberEntityPK that = (TourGroupMemberEntityPK) o;
        return tourGroupId == that.tourGroupId &&
                tourGroupMember == that.tourGroupMember;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tourGroupId, tourGroupMember);
    }
}
