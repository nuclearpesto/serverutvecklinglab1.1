package http;


import backend.data.viewmodels.requestviews.BefriendRequest;
import backend.data.viewmodels.requestviews.CreateUserRequest;
import backend.data.viewmodels.requestviews.LoginRequest;
import backend.data.viewmodels.resultviews.*;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by archer on 2016-11-17.
 */
public class Handler {
    public static final String BACKEND_BASE_URL;

    Client clien;

    static {

        String backendURL = System.getenv("BackendURL");
        if (backendURL != null)
            BACKEND_BASE_URL = backendURL;
        else
            BACKEND_BASE_URL = "http://localhost:8081";

    }

    // TODO: 2016-11-22 inject client? 
    public Handler() {
        clien = ClientBuilder
                .newBuilder()
                .register(JacksonFeature.class)
                .build();
    }


    public String getUserByEmail(String email) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9998").path("resource");

        Form form = new Form();
        form.param("x", "foo");
        form.param("y", "bar");
        return "";
    }


    public LoginResult login(String email, String password) {


        LoginRequest r = new LoginRequest();
        r.setEmail(email);
        r.setPassword(password);
        LoginResult result = new LoginResult();
        try {
            WebTarget target = clien.target(BACKEND_BASE_URL).path("/user/login");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(r));

            result = resp.readEntity(LoginResult.class);

            System.out.println("RESULT OF CALL IS " + result);

        } catch (ProcessingException ex) {
            result.setLogin(false);
            result.setReason("recieved garbage from server");
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setLogin(false);
            result.setReason("cant read from invalid requests");
            //something went terribad wrong

        } finally {
            clien.close();
        }

        return result;

    }

    public CreateUserResult register(String email, String password, String name) {

        CreateUserRequest req = new CreateUserRequest();
        req.setEmail(email);
        req.setPassword(password);
        req.setName(name);
        CreateUserResult result = new CreateUserResult();
        try {
            WebTarget target = clien.target(BACKEND_BASE_URL).path("/user/new");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(req));

            result = resp.readEntity(CreateUserResult.class);

            System.out.println("RESULT OF CALL IS " + result);

        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setReason("recieved garbage from server");
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setReason("cant read from invalid requests");
            //something went terribad wrong

        } finally {
            clien.close();
        }
        return result;


    }

    public BefriendUserResult befriendUser(BefriendRequest befriendRequest) {
        BefriendUserResult result = new BefriendUserResult();
        try {
            WebTarget target = clien.target(BACKEND_BASE_URL).path("/user/addfriend");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(befriendRequest));

            result = resp.readEntity(BefriendUserResult.class);

            System.out.println("RESULT OF CALL IS " + result);

        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setReason("recieved garbage from server");
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setReason("cant read from invalid requests");
            //something went terribad wrong

        } finally {
            clien.close();
        }
        return result;

    }


    public List<UserResult> listUsers() {

      List<UserResult> result = new ArrayList<UserResult>();
        try {
            WebTarget target = clien.target(BACKEND_BASE_URL).path("/user/list-users");
            result = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<UserResult>>(){});

        } catch (ProcessingException ex) {
           // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
           //something went terribad wrong

        } finally {
            clien.close();
        }
        return result;
    }


}
