package managedbeans;

import backend.data.generalviews.PostView;
import backend.data.resultviews.WallResult;
import http.Handler;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by archer on 2016-11-23.
 */

@ManagedBean(name = "wallBean", eager = true)
public class WallBean {

    public WallResult wall;
    public int currentOfffset;
    private static final int postsPerPage = 10;
    private String newPostText;

    @ManagedProperty(value = "#{user}")
    UserBean user;

    public WallBean() {

    }


    @PostConstruct
    public void init()
    {
        wall = getWallWithOffset(0);
    }


    public void setNewPostText(String newPostText) {
        this.newPostText = newPostText;
    }

    public String getNewPostText() {

        return newPostText;
    }

    public static int getPostsPerPage() {
        return postsPerPage;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public WallResult getWall() {
        return wall;
    }

    public void setWall(WallResult wall) {
        this.wall = wall;
    }

    public void getMoreWall(){
        wall.getPostViews().addAll(getWallWithOffset(currentOfffset).getPostViews());
    }

    public int getCurrentOfffset() {
        return currentOfffset;
    }

    public void setCurrentOfffset(int currentOfffset) {
        this.currentOfffset = currentOfffset;
    }

    public WallResult getWallWithOffset(int offset){
        Handler h = new Handler();
        return h.getWallWithOffset(offset,postsPerPage,user.getUsername());
    }

    public void post(){
        Handler h = new Handler();
        h.createPost(newPostText,user.getUsername());
    }

}
