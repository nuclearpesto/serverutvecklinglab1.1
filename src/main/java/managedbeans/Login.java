package managedbeans;

import http.Handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by archer on 2016-11-19.
 */

@ManagedBean(name="loginBean")
@RequestScoped
public class Login implements Serializable {
    private String email;
    private String pwd;
    private String msg;
    private boolean success;

    @ManagedProperty(value="#{user}")
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

   public void login(){
        Handler h = new Handler();
        if(h.login(email, pwd)){
            success = true;
            user.setLoggedIn(true);
            user.setUsername(email);
        }else{
            msg="OMG CANT LOGIN YAO";
        }

    }


}
