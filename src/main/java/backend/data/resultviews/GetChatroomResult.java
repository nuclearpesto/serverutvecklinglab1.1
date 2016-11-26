package backend.data.resultviews;


import backend.data.generalviews.ChatRoomView;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class GetChatroomResult extends Result{
    private ChatRoomView chatRoom;

    public GetChatroomResult(boolean success, String message, ChatRoomView chatRoom) {
        super(success, message);
        this.chatRoom = chatRoom;
    }

    public GetChatroomResult() {
    }

    public ChatRoomView getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoomView chatRoom) {
        this.chatRoom = chatRoom;
    }

}
