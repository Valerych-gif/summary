package lesson5;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SFGetter {

    public static SessionFactory getSessionFactory(){
        Configuration cfg = new Configuration().
                addResource("hibernate.cfg.xml").configure();
        return cfg.buildSessionFactory();
    }
}
