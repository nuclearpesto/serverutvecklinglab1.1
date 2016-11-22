package managedbeans;

import backend.data.viewmodels.requestviews.BefriendRequest;
import backend.data.viewmodels.resultviews.BefriendUserResult;
import backend.data.viewmodels.resultviews.UserResult;
import http.Handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;


/**
 * Created by archer on 2016-11-22.
 */
@ManagedBean( name="managerBean", eager=true)
@RequestScoped
public class managerBean {

    public String getUserEmail() {
        return useremail;
    }

    public void setUserEmail(String userEmail) {
        this.useremail = userEmail;
    }

    private String useremail;
    private List<UserResult> users;
    public managerBean() {
        try{
            System.out.println("CREATING MANAGER BEAN");
            this.users=listUsers();
            System.out.println("USERS ARE NOW STORED AS" + users);
        }catch (Exception e){

        }
    }


    public List<UserResult> getUsers() {
        System.out.println("GETTING USERS FOR DISPLAY" );
        System.out.println("USERS LIST IS " + users);
        return this.users;
    }

    public void setUsers(List<UserResult> users) {
        this.users = users;
    }

    public List<UserResult> listUsers(){
        Handler h = new Handler();
        System.out.println("GOT USERS SUCCESSFULLY");
        return h.listUsers();
    }

    public void befriendUser(){
        Handler h = new Handler();
        BefriendRequest req =  new BefriendRequest();
        req.setUser1Email("pelle@pelle.pe");
        req.setUser2Email(useremail);
        h.befriendUser(req );
    }

}
