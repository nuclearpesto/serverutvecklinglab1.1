package backend.data.viewmodels.resultviews;

/**
 * Created by archer on 2016-11-20.
 */
public class RegisterResult {

    boolean success;
    String reason;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
