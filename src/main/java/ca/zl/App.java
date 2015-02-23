package ca.zl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ca.zl.domain.Computer;

public class App 
{
  public static void main(String[] args) {

    App app = new App();
    app.init();
  }

  public void init() {
    Computer c1 = populateComputer("Dell", "D386");
    Computer c2 = populateComputer("IBM", "I386");
    Computer c3 = populateComputer("Dell", "D486");
    Computer c4 = populateComputer("HP", "H586");
    System.out.println("Done");
  }

  private Computer populateComputer(String maker, String model) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();
    Computer computer = new Computer();
    computer.setMaker(maker);
    computer.setModel(model);
    session.save(computer);
    transaction.commit();
    return computer;
  }
}