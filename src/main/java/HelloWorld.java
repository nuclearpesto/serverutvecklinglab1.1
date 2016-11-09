
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "helloWorld",eager=true)
@SessionScoped
public class HelloWorld {
   public HelloWorld() {
      System.out.println("HelloWorld started!");
   }

   private String userName="Nanashi";

   public String getUserName() {
       return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String replyHello() {
       return "Hello"+userName;
   }

   public String getMessage() {
      return "Hello World!";
   }
}
