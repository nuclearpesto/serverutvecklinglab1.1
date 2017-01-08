package backend.data.generalviews;

import com.google.appengine.api.datastore.Key;

/**
 * Created by simonlundstrom on 16/11/16.
 */
public class PostView {
    String text, date, userId;
    Key postId;

    public PostView() {
    }

    public PostView(String text, String date, String userId, Key postId) {
        this.text = text;
        this.date = date;
        this.userId = userId;
        this.postId = postId;
        System.out.println("CREATED A POST RESULT " + toString());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Key getPostId() {
        return postId;
    }

    public void setPostId(Key postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "PostView{" +
                "text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", userId='" + userId + '\'' +
                ", postId=" + postId +
                '}';
    }
}
