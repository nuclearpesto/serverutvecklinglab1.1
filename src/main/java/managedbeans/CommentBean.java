package managedbeans;

import backend.data.generalviews.PostView;
import backend.data.requestviews.CreateCommentRequest;
import backend.data.resultviews.CommentListResult;
import backend.data.resultviews.GetPostResult;
import http.Handler;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by archer on 2016-11-24.
 */
@ManagedBean(name = "commentBean")
public class CommentBean {

    CommentListResult comments;
    @ManagedProperty(value = "#{user}")
    UserBean user;
    String newCommentText;
    int postid;
    GetPostResult post;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        try {
            postid = Integer.parseInt(params.get("postid"));
        } catch (NumberFormatException ex) {
            postid = -1;
        }


        comments = new Handler().getCommentsBypostId(postid);
        post = new Handler().getPostById(postid);
    }

    public String getNewCommentText() {
        return newCommentText;
    }

    public GetPostResult getPost() {
        return post;
    }

    public void setPost(GetPostResult post) {
        this.post = post;
    }

    public void setNewCommentText(String newCommentText) {
        this.newCommentText = newCommentText;
    }

    public CommentListResult getComments() {
        return comments;
    }

    public void setComments(CommentListResult comments) {
        this.comments = comments;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void newComment() {
        System.out.println("NYU FAOSDFGKSDFHNGÖKSADRASDRJHGÖKLSSDFHKÖHRGBERGBHAÖKSTGASEGHHEROHKOÅÖK");
        Handler h = new Handler();
        h.comment(new CreateCommentRequest(newCommentText, user.getUsername(), postid));
    }
}
