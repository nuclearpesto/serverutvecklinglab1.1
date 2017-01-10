package managedbeans;

import backend.data.generalviews.ChatMessageView;
import backend.data.resultviews.GetChatroomResult;
import backend.data.resultviews.Result;
import http.Handler;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.Map;

/**
 * Created by simonlundstrom on 26/11/16.
 */
@ManagedBean(name="singlechatbean")
@ViewScoped
public class SingleChatBean {
    GetChatroomResult chatRoom;
    String currentChatroom;
    String idOfLastMessage;
    String newMessage;

    @ManagedProperty(value = "#{user}")
    UserBean user;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        try {
            currentChatroom = params.get("roomId");
        }catch(NumberFormatException nfe) {
            System.out.println("Kunde ej omvandla rumid till en nuffra.");
        }
        chatRoom = new Handler().getChatroomById(currentChatroom);
        System.out.println("SingleChatBean reporst: chatroom is "+chatRoom);
    }
    public void refreshChat(){
        chatRoom = new Handler().getChatroomById(currentChatroom);
    }

    public void postMessage(String mess){
        Result result = new Handler().postToChatroom(user.getUsername(),mess,currentChatroom);
        chatRoom.getChatRoom().getMessages().add(new ChatMessageView(mess,user.getUsername(),new Date(),null,null));
    }

    public void postMessage() {
        postMessage(newMessage);
    }

    public GetChatroomResult getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(GetChatroomResult chatRoom) {
        this.chatRoom = chatRoom;
    }

    public String getCurrentChatroom() {
        return currentChatroom;
    }

    public void setCurrentChatroom(String currentChatroom) {
        this.currentChatroom = currentChatroom;
    }

    public String getIdOfLastMessage() {
        return idOfLastMessage;
    }

    public void setIdOfLastMessage(String idOfLastMessage) {
        this.idOfLastMessage = idOfLastMessage;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
