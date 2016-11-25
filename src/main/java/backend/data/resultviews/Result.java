package backend.data.resultviews;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class Result {
    String message;
    boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public Result() {
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
