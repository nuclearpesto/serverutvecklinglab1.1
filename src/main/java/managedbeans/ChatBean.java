package managedbeans;

import backend.data.resultviews.ListChatRoomsResult;
import http.Handler;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by simonlundstrom on 26/11/16.
 */
@ManagedBean(name="chatbean")
public class ChatBean {
    ListChatRoomsResult chatrooms;

    @ManagedProperty(value = "#{user}")
    UserBean user;

    @PostConstruct
    public void init() {
        chatrooms = new Handler().listChatroomsByUser(user.getUsername());
    }

    public ListChatRoomsResult getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(ListChatRoomsResult chatrooms) {
        this.chatrooms = chatrooms;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
    public void createChat(String friendEmail) {
        new Handler().createChat(user.getUsername(),friendEmail,user.getUsername().substring(0,4)+" & "+friendEmail.substring(0,4)+" caht!");
    }
}
