package foo;

import javax.persistence.*;

/**
 * Created by simonlundstrom on 09/11/16.
 */

@Entity
@Table(name="STUDENT")
public class Student {
    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
