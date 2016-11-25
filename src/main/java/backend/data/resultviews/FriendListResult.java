package backend.data.resultviews;


import backend.data.generalviews.UserView;

import java.util.List;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class FriendListResult extends Result{
    List<UserView> friends;

    public List<UserView> getFriends() {
        return friends;
    }

    public void setFriends(List<UserView> friends) {
        this.friends = friends;
    }

    public FriendListResult(boolean success, String message, List<UserView> friends) {
        super(success, message);
        this.friends = friends;
    }

    public FriendListResult() {
    }

    @Override
    public String toString() {
        return super.toString() + "FriendListResult{" +
                "friends=" + friends +
                '}';
    }
}
