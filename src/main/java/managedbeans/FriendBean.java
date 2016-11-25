package managedbeans;

import backend.data.generalviews.UserView;
import backend.data.requestviews.BefriendRequest;
import http.Handler;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by archer on 2016-11-22.
 */
@ManagedBean(name = "friendBean", eager = true)
@ViewScoped
public class FriendBean {

    @ManagedProperty(value = "#{user}")
    private UserBean user;

    //used for selecting a user email on home.xhtml
    private String useremail;

    //used for searching
    private String searchString;


    //used so home.xhtml can list the searchResult
    private List<UserView> searchResult;
    private List<UserView> friends;

    public FriendBean() {

    }

    @PostConstruct
    public void init() {
        this.friends = loadFriends();
        this.searchResult = new ArrayList<UserView>();

    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
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

    public List<UserView> getSearchResult() {
        System.out.println("SEARCHRESULT CONTATINS THIS WHEN GOTTEN" + searchResult);
        return this.searchResult;
    }

    public void setSearchResult(List<UserView> searchResult) {
        this.searchResult = searchResult;
    }


    public List<UserView> getFriends() {
        return friends;
    }

    public void setFriends(List<UserView> friends) {
        this.friends = friends;
    }

    public List<UserView> listUsers() {
        Handler h = new Handler();
        return h.listUsers();
    }

    public List<UserView> loadFriends() {
        Handler h = new Handler();
        // return h.getFriends(user.getUsername());
        return h.getFriends(user.getUsername()).getFriends();
    }


    public void befriendUser(String email) {

        System.out.println("BEFRIEND USER WIHTOUT ARGUMENTS CALLED , EMAIL IS " + email);

        Handler h = new Handler();
        BefriendRequest req = new BefriendRequest();
        req.setUser1Email(user.getUsername());
        req.setUser2Email(email);
        h.befriendUser(req);
    }


    public void unbefriendUser(String email) {

        System.out.println("UNBEFRIEND USER WIHT EMAIL " + email);

        Handler h = new Handler();
        BefriendRequest req = new BefriendRequest();
        req.setUser1Email(user.getUsername());
        req.setUser2Email(email);
        h.unbefriendUser(req);
    }

    public void search() {

        Handler h = new Handler();
        searchResult = h.searchUsers(searchString);
        System.out.println("SEARCH RESULT IS " + searchResult);

    }

}
