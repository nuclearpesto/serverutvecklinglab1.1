package managedbeans;

import backend.data.requestviews.BefriendRequest;
import backend.data.resultviews.GetUserResult;
import http.Handler;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;


/**
 * Created by archer on 2016-11-22.
 */
@ManagedBean(name = "friendBean", eager = true)
@RequestScoped
public class FriendBean {

    @ManagedProperty(value = "#{user}")
    private UserBean user;

    //used for selecting a user email on home.xhtml
    private String useremail;

    //used so home.xhtml can list the allUsers
    private List<GetUserResult> allUsers;
    private List<GetUserResult> friends;

    public FriendBean() {

    }
    @PostConstruct
    public void init(){
        try {
            this.allUsers = listUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.friends = loadFriends();
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserEmail() {
        return useremail;
    }

    public void setUserEmail(String userEmail) {
        this.useremail = userEmail;
    }

    public List<GetUserResult> getAllUsers() {
        return this.allUsers;
    }

    public void setAllUsers(List<GetUserResult> allUsers) {
        this.allUsers = allUsers;
    }


    public List<GetUserResult> getFriends() {
        return friends;
    }

    public void setFriends(List<GetUserResult> friends) {
        this.friends = friends;
    }

    public List<GetUserResult> listUsers() {
        Handler h = new Handler();
        return h.listUsers();
    }

    public List<GetUserResult> loadFriends() {
        Handler h = new Handler();
       // return h.getFriends(user.getUsername());
        return h.getFriends(user.getUsername()).getFriends();
    }


    public void befriendUser() {
        Handler h = new Handler();
        BefriendRequest req = new BefriendRequest();
        req.setUser1Email(user.getUsername());
        req.setUser2Email(useremail);
        h.befriendUser(req);
    }

}
