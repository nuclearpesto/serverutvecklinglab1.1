package http;


import backend.data.generalviews.ChatRoomView;
import backend.data.generalviews.PostView;
import backend.data.generalviews.UserView;
import backend.data.requestviews.*;
import backend.data.resultviews.*;
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

    Client client;

    static {

        String backendURL = System.getenv("BACKEND_BASE_URL");
        if (backendURL != null)
            BACKEND_BASE_URL = backendURL;
        else
            BACKEND_BASE_URL = "http://localhost:8081";

    }

    // TODO: 2016-11-22 inject client? 
    public Handler() {
        client = ClientBuilder
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
            WebTarget target = client.target(BACKEND_BASE_URL).path("/user/login");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(r));

            result = resp.readEntity(LoginResult.class);

            System.out.println("RESULT OF CALL IS " + result);

        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server:" + ex.getMessage());
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests");
            //something went terribad wrong

        } finally {
            client.close();
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
            WebTarget target = client.target(BACKEND_BASE_URL).path("/user/new");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(req));

            result = resp.readEntity(CreateUserResult.class);

            System.out.println("RESULT OF CALL IS " + result);

        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server" + ex.getMessage());
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests" + ex.getMessage());
            //something went terribad wrong

        } finally {
            client.close();
        }
        return result;


    }

    public BefriendUserResult befriendUser(BefriendRequest befriendRequest) {
        BefriendUserResult result = new BefriendUserResult();
        try {
            WebTarget target = client.target(BACKEND_BASE_URL).path("/user/addfriend");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(befriendRequest));

            result = resp.readEntity(BefriendUserResult.class);

            System.out.println("RESULT OF CALL IS " + result);

        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server");
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests");
            //something went terribad wrong

        } finally {
            client.close();
        }
        return result;

    }


    public List<UserView> listUsers() {

        List<UserView> result = new ArrayList<UserView>();
        try {
            WebTarget target = client.target(BACKEND_BASE_URL).path("/user/listusers");
            result = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<UserView>>() {
            });

        } catch (ProcessingException ex) {
            // unable to map recieved json to pojo
            ex.printStackTrace();

        } catch (IllegalStateException ex) {
            //something went terribad wrong
            ex.printStackTrace();
        } finally {
            client.close();
        }
        return result;
    }


    public FriendListResult getFriends(String userEmail) {
        FriendListResult result = new FriendListResult();
        try {

            WebTarget target = client.target(BACKEND_BASE_URL).path("/user/friendlist").queryParam("id", userEmail);
            result = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get().readEntity(FriendListResult.class);


            System.out.println("RESULT OF CALL IS " + result);

        } catch (ProcessingException ex) {
            ex.printStackTrace();
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            //something went terribad wrong

        } finally {
            client.close();
        }
        return result;
    }


    public WallResult getWallWithOffset(int offset, int amount, String email) {
        WallResult result = new WallResult();
        try {
            WallRequest request = new WallRequest();
            request.setAmountOfPosts(amount);
            request.setStartAt(offset);
            request.setUserEmail(email);
            WebTarget target = client.target(BACKEND_BASE_URL).path("/post/getwall");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(request));

            result = resp.readEntity(WallResult.class);
        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server " +ex.getMessage());
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests " + ex.getMessage());
            //something went terribad wrong

        } finally {
            client.close();
        }
        System.out.println("RESULT OF GET WALL CALL IS " + result);
        return result;
    }


    public CreatePostResult createPost(String text, String userEmail) {

        CreatePostResult result = new CreatePostResult();
        CreatePostRequest request = new CreatePostRequest();
        try {
            request.setPostText(text);
            request.setUserEmail(userEmail);
            WebTarget target = client.target(BACKEND_BASE_URL).path("/post/create");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(request));

            result = resp.readEntity(CreatePostResult.class);

            System.out.println("RESULT OF CALL IS " + result);

        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server");
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests");
            //something went terribad wrong
        } finally {
            client.close();
        }
        return result;
    }

    public void logout(String username) {
        WebTarget target = client.target(BACKEND_BASE_URL).path("/user/logout").queryParam("id", username);
        Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get();
    }

    public List<UserView> searchUsers(String searchString) {

        WebTarget target = client.target(BACKEND_BASE_URL).path("/user/listusers").queryParam("searchString", searchString).queryParam("from",1).queryParam("amount",100);
        return target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get().readEntity(new GenericType<List<UserView>>() {
        });


    }

    public Result unbefriendUser(BefriendRequest req) {
        Result result = new Result();
        try {
            WebTarget target = client.target(BACKEND_BASE_URL).path("/user/removeFriend");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.json(req));
            result = resp.readEntity(Result.class);


        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server" + ex.getMessage());
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests");
            //something went terribad wrong

        } finally {
            client.close();
        }


        System.out.println("RESULT OF CALL IS " + result);
        return result;
    }

    public Result comment(CreateCommentRequest cre) {
        Result result = new Result();
        try {
            WebTarget target = client.target(BACKEND_BASE_URL).path("/comment/post");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(cre));
            result = resp.readEntity(Result.class);


        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server" + ex.getMessage());
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests");
            //something went terribad wrong

        } finally {
            client.close();
        }
        System.out.println("RESULT OF CREATE COMMENT CALL IS " + result);
        return result;

    }

    public CommentListResult getCommentsBypostId(String postid) {
        CommentListResult result = new CommentListResult();
        try {
            WebTarget target = client.target(BACKEND_BASE_URL).path("/comment/get").queryParam("PostId", postid);
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .get();
            result = resp.readEntity(CommentListResult.class);


        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server" + ex.getMessage());
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests");
            //something went terribad wrong

        } finally {
            client.close();
        }
        System.out.println("RESULT OF CALL IS " + result);
        return result;
    }

    public List<PostView> getPostsByUser(String userid) {
        WebTarget target = client.target(BACKEND_BASE_URL).path("/post/getbyuser").queryParam("userid", userid);
        return target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get().readEntity(new GenericType<List<PostView>>() {
        });

    }

    public GetPostResult getPostById(String postid) {
        WebTarget target = client.target(BACKEND_BASE_URL).path("/post/get").queryParam("id", postid);
        return target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get().readEntity(GetPostResult.class);

    }

    public ListChatRoomsResult listChatroomsByUser(String username) {
        WebTarget target = client.target(BACKEND_BASE_URL).path("/chat/listmyrooms").queryParam("user", username);
        return target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get().readEntity(ListChatRoomsResult.class);
    }

    public CreateChatRoomResult createChat(String username, String friendEmail, String s) {
        CreateChatRoomResult result = new CreateChatRoomResult();
        CreateChatroomRequest req = new CreateChatroomRequest(username, friendEmail, s);
        try {
            WebTarget target = client.target(BACKEND_BASE_URL).path("/chat/create");
            Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(req));
            result = resp.readEntity(CreateChatRoomResult.class);
        } catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server" + ex.getMessage());
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests");
            //something went terribad wrong

        } finally {
            client.close();
        }
        System.out.println("RESULT OF CREATECHATROOM IS " + result);
        return result;
    }

    public GetChatroomResult getChatroomById(String currentChatroom) {
        GetChatroomResult result = new GetChatroomResult();
        try {
            WebTarget target = client.target(BACKEND_BASE_URL).path("/chat/get").queryParam("roomId", currentChatroom);
            result = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get().readEntity(GetChatroomResult.class);
        }catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server" + ex.getMessage());
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests");
            //something went terribad wrong
        } finally {
            client.close();
        }
        System.out.println("RESULT OF GET_CHATROOM IS " + result);
        return result;
    }

    public Result postToChatroom(String username, String mess, String currentChatroom) {
        Result result = new Result();
        System.out.println("GONNA POST MESSAGE " + mess + " TO ROOM WITH ID " + currentChatroom + " WIHT USERNAME " + username);
        ChatPostRequest req = new ChatPostRequest(username,mess,currentChatroom);
        try {
            WebTarget target = client.target(BACKEND_BASE_URL).path("/chat/post");
            result = target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(req)).readEntity(Result.class);
        }catch (ProcessingException ex) {
            result.setSuccess(false);
            result.setMessage("recieved garbage from server" + ex.getMessage());
            // unable to map recieved json to pojo
        } catch (IllegalStateException ex) {
            result.setSuccess(false);
            result.setMessage("cant read from invalid requests");
            //something went terribad wrong
        } finally {
            client.close();
        }
        System.out.println("RESULT OF POST_TO_CHATROM IS " + result);
        return result;
    }
}
