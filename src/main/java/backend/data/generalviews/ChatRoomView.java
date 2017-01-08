package backend.data.generalviews;

import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatRoomView {
    private String name;
    private long id;
    private List<UserView> members;
    private List<ChatMessageView> messages;

    public ChatRoomView(String name, long id, List<UserView> members, List<ChatMessageView> messages) {
        this.name = name;
        this.id = id;
        this.members = members;
        this.messages = messages;
    }

    public ChatRoomView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<UserView> getMembers() {
        return members;
    }

    public void setMembers(List<UserView> members) {
        this.members = members;
    }

    public List<ChatMessageView> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessageView> messages) {
        this.messages = messages;
    }
}
