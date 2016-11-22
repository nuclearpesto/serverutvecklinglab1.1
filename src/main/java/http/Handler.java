package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by archer on 2016-11-17.
 */
public class Handler {
    public static final String BACKEND_BASE_URL;

    public Handler() {
        String backendURL = System.getenv("BackendURL");
        if (backendURL != null)
            BACKEND_BASE_URL = backendURL;
        else
            BACKEND_BASE_URL = "http://localhost:8081";
        // TODO: 2016-11-19 get ip/host address of backend via some sort of system variable
    }


    public String getUserByEmail(String email) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9998").path("resource");

        Form form = new Form();
        form.param("x", "foo");
        form.param("y", "bar");

        MyJAXBBean bean =
                target.request(MediaType.APPLICATION_JSON_TYPE)
                        .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                                MyJAXBBean.class);
    }

    public boolean login(String email, String pw) {
        return true;
    }

}
