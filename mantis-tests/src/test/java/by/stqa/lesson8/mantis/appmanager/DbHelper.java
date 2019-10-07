package by.stqa.lesson8.mantis.appmanager;

import by.stqa.lesson8.mantis.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper{
    private final SessionFactory sessionFactory;

    public DbHelper(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public List<User> users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery( "from User where access_level <> 90" ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}
