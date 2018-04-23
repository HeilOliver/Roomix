package at.fhv.roomix.domain.guest.model;

import java.util.Collection;

public class RoomDomain {
    private int roomId;
    private int roomCategory;
    private String status;
    private RoomCategoryDomain roomCategoryByRoomCategory;
    private Collection<RoomAssignmentDomain> roomAssignmentsByRoomId;
    private Proxy<Collection<RoomAssignmentDomain>, Integer> roomAssignmentProxy;


    public void setRoomAssignmentProxy(Proxy<Collection<RoomAssignmentDomain>, Integer> roomAssignmentProxy) {
        this.roomAssignmentProxy = roomAssignmentProxy;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(int roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RoomCategoryDomain getRoomCategoryByRoomCategory() {
        return roomCategoryByRoomCategory;
    }

    public void setRoomCategoryByRoomCategory(RoomCategoryDomain roomCategoryByRoomCategory) {
        this.roomCategoryByRoomCategory = roomCategoryByRoomCategory;
    }

    public Collection<RoomAssignmentDomain> getRoomAssignmentsByRoomId() {
        if (roomAssignmentProxy != null) {
            return (roomAssignmentsByRoomId = roomAssignmentProxy.get());
        } else {
            return roomAssignmentsByRoomId;
        }
    }

    public void setRoomAssignmentsByRoomId(Collection<RoomAssignmentDomain> roomAssignmentsByRoomId) {
        this.roomAssignmentsByRoomId = roomAssignmentsByRoomId;
    }
}
