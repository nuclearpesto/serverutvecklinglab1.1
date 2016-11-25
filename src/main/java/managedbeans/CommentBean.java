package managedbeans;

import backend.data.generalviews.CommentView;
import backend.data.resultviews.CommentListResult;
import http.Handler;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by archer on 2016-11-24.
 */
@ManagedBean(name = "commentBean")
public class CommentBean {

    CommentListResult comments;
    @ManagedProperty(value = "#{user}")
    UserBean user;

    @PostConstruct
    public void init(){

        comments = new CommentListResult();
        List<CommentView> commentsviews;
        commentsviews = new ArrayList<>();

        commentsviews.add(new CommentView("hello","igår",1,2,"pelle@pelle.pe"));
        commentsviews.add(new CommentView("hello","igår",1,2,"pelle@pelle.pe"));
        commentsviews.add(new CommentView("hello","igår",1,2,"pelle@pelle.pe"));
        commentsviews.add(new CommentView("hello","igår",1,2,"pelle@pelle.pe"));
        commentsviews.add(new CommentView("hello","igår",1,2,"pelle@pelle.pe"));
        commentsviews.add(new CommentView("hello","igår",1,2,"pelle@pelle.pe"));
        commentsviews.add(new CommentView("hello","igår",1,2,"pelle@pelle.pe"));

        comments.setList(commentsviews);
        //comments=  new Handler().getPosts();
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
}
