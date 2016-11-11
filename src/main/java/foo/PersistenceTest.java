package foo;

import javax.persistence.*;

/**
 * Created by simonlundstrom on 11/11/16.
 */
public class PersistenceTest {
    private static EntityManagerFactory prylChefsFabrik;
    private static EntityManager prylchef;
    static {
        try {
            prylChefsFabrik = Persistence.createEntityManagerFactory("TestMupp");
            prylchef = prylChefsFabrik.createEntityManager();
        }
        catch(Exception ex) {
            System.err.println("Fel utav bara helvete: "+ex);
            ex.printStackTrace();
        }
    }

    public String saveStudent() {
        if (!prylchef.isOpen()) System.out.println("WHAT THE FUCK!?");
        EntityTransaction transa = prylchef.getTransaction();
        transa.begin();
        prylchef.setFlushMode(FlushModeType.COMMIT);
        Student tempStudent = new Student("Pelle", 98);
        prylchef.persist(tempStudent);
        transa.commit();
        prylchef.close();
        prylChefsFabrik.close();
        return "Did stuff";
    }
}
