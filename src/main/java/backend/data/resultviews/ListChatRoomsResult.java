package backend.data.resultviews;


import backend.data.generalviews.ChatroomMetaData;

import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ListChatRoomsResult extends Result{
    @Override
    public String toString() {
         return super.toString() +"ListChatRoomsResult{" +
                "rooms=" + rooms +
                '}';
    }

    List<ChatroomMetaData> rooms;

    public ListChatRoomsResult(boolean success, String message, List<ChatroomMetaData> rooms) {
        super(success, message);
        this.rooms = rooms;
    }

    public ListChatRoomsResult() {
    }

    public List<ChatroomMetaData> getRooms() {
        return rooms;
    }

    public void setRooms(List<ChatroomMetaData> rooms) {
        this.rooms = rooms;
    }
}
