package managedbeans;

import backend.data.viewmodels.resultviews.CreateUserResult;
import backend.data.viewmodels.resultviews.LoginResult;
import backend.data.viewmodels.resultviews.RegisterResult;
import http.Handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
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
        LoginResult result = h.login(email, pwd);

       if(result.isLogin()){
            success = true;
            user.setLoggedIn(true);
            user.setUsername(email);
        }else{
            success = false;
           user.setLoggedIn(false);
           user.setUsername(null);
           msg=result.getReason();
        }

    }
    public void register(){
        System.out.println("REGISTER CALLED");
        Handler h = new Handler();
        CreateUserResult result = h.register(email, pwd, name);

          if(result.isSuccess()){
            success = true;
            user.setLoggedIn(true);
            user.setUsername(email);
        }else{
            success = false;
           user.setLoggedIn(false);
           user.setUsername(null);
           msg=result.getReason();
        }

    }

    public void redirectUserIfNotLoggedIn(ComponentSystemEvent event){
        if(user==null || !user.isLoggedIn){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
