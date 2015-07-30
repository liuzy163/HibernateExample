package ca.zl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import ca.zl.domain.Computer;
import ca.zl.domain.Employee;

public class SessionFactoryRule implements MethodRule {

  private SessionFactory sessionFactory;
  private Transaction transaction;
  private Session session;

  public Statement apply(final Statement base, FrameworkMethod method, Object target) {
    return new Statement() {

      @Override
      public void evaluate() throws Throwable {
        sessionFactory = createSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
          base.evaluate();
        } finally {
          try {
            session.close();
            sessionFactory.close();
          } catch (HibernateException e) {
            e.printStackTrace();
          }
        }
      }
    };
  }

  public Session getSession() {
    return session;
  }

  public void commit() {
    transaction.commit();
  }

  private SessionFactory createSessionFactory() {
    Configuration configuration = new Configuration();

    configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
    configuration.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
    configuration.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:testdb;shutdown=true");
    configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    configuration.setProperty("show_sql", "true");
    configuration.setProperty("format_sql", "true");
    configuration.addAnnotatedClass(Computer.class);
    configuration.addAnnotatedClass(Employee.class);

    StandardServiceRegistryBuilder builder =
        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
    return sessionFactory;
  }
}
