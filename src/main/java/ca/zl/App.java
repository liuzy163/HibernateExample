package ca.zl;



import org.hibernate.Session;
import org.hibernate.Transaction;

import ca.zl.domain.Computer;

public class App {

  public static void main(String[] args) {
    Computer computer = new Computer();
    computer.setMaker("HP");
    computer.setModel("Dualcore");

    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(computer);
    session.flush();
    transaction.commit();
    session.close();
    HibernateUtil.getSessionFactory().close();
  }
}
