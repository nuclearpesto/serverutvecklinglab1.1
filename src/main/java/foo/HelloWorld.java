package foo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "helloWorld", eager = true)
@SessionScoped
public class HelloWorld {
    public HelloWorld() {
        System.out.println("foo.HelloWorld started! " + System.getProperty("user.dir"));
    }

    private String userName = "Nanashi";
    private String student = "nuffeli";
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String replyHello() {
        return "Hello" + student;
    }

    public String getMessage() {
        return "Hello World!";
    }

    public void setStudent(String s) {
        student = s;
    }

    public String getStudent() {
        return student;
    }

    public void extraSetStudent() {
        new PersistenceTest().saveStudent(student,age);
    }

}
