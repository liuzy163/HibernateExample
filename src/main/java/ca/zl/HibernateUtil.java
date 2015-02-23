package ca.zl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

  private static SessionFactory sessionFactory;

  private static SessionFactory buildSessionFactory() {
    try {
      Configuration configuration = new Configuration();
      configuration.configure();
      ServiceRegistry serviceRegistry =
          new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
      // The config looks for hibernate.cfg.xml from resources
      return new Configuration().configure().buildSessionFactory(serviceRegistry);
    } catch (Throwable ex) {
      System.err.println("Exception occurred when initializing session factory." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      sessionFactory = buildSessionFactory();
    }
    return sessionFactory;
  }

  public static void shutdown() {
    getSessionFactory().close();
  }

}