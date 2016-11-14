package foo;

import javax.persistence.*;

/**
 * Created by simonlundstrom on 11/11/16.
 */
public class PersistenceTest {
    private static EntityManagerFactory prylChefsFabrik = Persistence.createEntityManagerFactory("TestMupp");
    private static EntityManager prylchef;

    public PersistenceTest() {
        try {
            prylchef = prylChefsFabrik.createEntityManager();
        }
        catch(Exception ex) {
            System.err.println("Kunde inte fixa EntityManagern/Fabriken: "+ex);
            ex.printStackTrace();
        }
    }

    public String saveStudent(String name, int age) {
        try {
            if (!prylchef.isOpen()) System.out.println("WHAT THE FUCK!?");
            EntityTransaction transa = prylchef.getTransaction();
            transa.begin();
            prylchef.setFlushMode(FlushModeType.COMMIT);
            Student testStudent = new Student(name, age);
            prylchef.persist(testStudent);
            transa.commit();
            prylchef.close();
        }
        catch (Exception e) {
            e.printStackTrace();;
        }
        return "Did stuff";
    }
}
