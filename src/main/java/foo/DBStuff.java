package foo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.spi.HibernateEntityManagerFactoryAware;

/**
 * Created by simonlundstrom on 09/11/16.
 */
public class DBStuff {
    private static SessionFactory sessionsfabrik= null;
    static {
        try {
            sessionsfabrik = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
        }
        catch(Throwable ex) {
            System.err.println(ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionsfabrik;
    }

    public String saveStudent() {
        Transaction tx=null;
        Session session=null;
        try {
            session = sessionsfabrik.openSession();
            tx = session.beginTransaction();
            Student tempStudent = new Student("Pelle", 98);
            session.save(tempStudent);
            tx.commit();
        }
        catch(HibernateException ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
        finally {
            session.close();
            return "Pelle";
        }
    }
}
