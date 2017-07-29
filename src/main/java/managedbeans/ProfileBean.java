package managedbeans;

import backend.data.generalviews.PostView;
import http.Handler;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

/**
 * Created by archer on 2016-11-25.
 */
@ManagedBean(name = "profileBean")
public class ProfileBean {
    String userid;

    List<PostView> posts;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<PostView> getPosts() {
        return posts;
    }

    public void setPosts(List<PostView> posts) {
        this.posts = posts;
    }
    @PostConstruct
    public void onload() {

        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        userid = params.get("userid");
        System.out.println("ONLOAD CALLED WITH " + userid);
        if(userid!=null){
            Handler h = new Handler();
            posts = h.getPostsByUser(userid);
        }
    }


    public ProfileBean() {

    }


}
