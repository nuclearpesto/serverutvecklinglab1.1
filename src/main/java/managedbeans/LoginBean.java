package managedbeans;

import backend.data.resultviews.CreateUserResult;
import backend.data.resultviews.LoginResult;
import http.Handler;

import javax.faces.bean.RequestScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by archer on 2016-11-19.
 */

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {
    private String email;
    private String pwd;
    private String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private boolean success;

    @ManagedProperty(value = "#{user}")
    private UserBean user;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void login() {
        Handler h = new Handler();
        LoginResult result = h.login(email, pwd);

        if (result.isSuccess()) {
            success = true;
            user.setLoggedIn(true);
            user.setUsername(email);
            FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("FacecrapLogin","{\"id\":\""+email+"\",\"token\":\""+result.getUuid()+"\"}",null);
        } else {
            success = false;
            user.setLoggedIn(false);
            user.setUsername(null);
            msg = result.getMessage();
        }
    }

    public void register() {
        System.out.println("REGISTER CALLED");
        Handler h = new Handler();
        CreateUserResult result = h.register(email, pwd, name);

        if (result.isSuccess()) {
            success = true;
            user.setLoggedIn(true);
            user.setUsername(email);
        } else {
            success = false;
            user.setLoggedIn(false);
            user.setUsername(null);
            msg = result.getMessage();
        }
    }

    public void redirectUserIfNotLoggedIn(ComponentSystemEvent event) {
        if (user == null || !user.isLoggedIn) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void logout() {

        if (user != null && user.isLoggedIn) {
            Handler h = new Handler();
            h.logout(user.getUsername());
            user.setLoggedIn(false);
            user.username = null;
            Map<String,Object> ingredienser = new HashMap<>();
            ingredienser.put("maxAge",new Integer(0));
            FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("FacecrapLogin","",ingredienser);
            try {
                System.out.println("Loggade ut.");
                FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("Du var inte ens inloggad!");
    }

}
