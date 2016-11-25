package backend.data.requestviews;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class CreateChatroomRequest {
    String ownerEmail, inviteeEmail, roomName;
    int id;

    public CreateChatroomRequest(String ownerEmail, String inviteeEmail, String roomName, int id) {
        this.ownerEmail = ownerEmail;
        this.inviteeEmail = inviteeEmail;
        this.id = id;
        this.roomName = roomName;
    }

    public CreateChatroomRequest() {
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getInviteeEmail() {
        return inviteeEmail;
    }

    public void setInviteeEmail(String inviteeEmail) {
        this.inviteeEmail = inviteeEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
