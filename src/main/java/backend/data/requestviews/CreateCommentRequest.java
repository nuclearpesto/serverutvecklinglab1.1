package backend.data.requestviews;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class CreateCommentRequest {
    String text,userEmail;
    String postId;

    public CreateCommentRequest() {
    }

    public CreateCommentRequest(String text, String userEmail, String postId) {
        this.text = text;
        this.userEmail = userEmail;
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
