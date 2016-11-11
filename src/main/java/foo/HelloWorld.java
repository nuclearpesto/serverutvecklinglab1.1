package foo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "helloWorld",eager=true)
@SessionScoped
public class HelloWorld {
   public HelloWorld() {
      System.out.println("foo.HelloWorld started! "+System.getProperty("user.dir"));
   }

   private String userName="Nanashi";
   private String student = "nuffeli";

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

   public void setStudent(String s) { student = s; }

   public String getStudent() { return new DBStuff().saveStudent();}
}
