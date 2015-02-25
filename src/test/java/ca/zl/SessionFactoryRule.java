package ca.zl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class SessionFactoryRule implements MethodRule {

  private SessionFactory sessionFactory;
  private Transaction transaction;
  private Session session;

  public Statement apply(final Statement base, FrameworkMethod method, Object target) {
    return new Statement() {

      @Override
      public void evaluate() throws Throwable {
        sessionFactory = HibernateUtil.getSessionFactory();
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

}
