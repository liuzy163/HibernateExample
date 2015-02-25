package ca.zl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Rule;
import org.junit.Test;

import ca.zl.domain.Computer;


public class CreateComputerTest {

  @Rule
  public SessionFactoryRule sessionFactoryRule = new SessionFactoryRule();

  @Test
  public void createComputerTable() {
    Session session = sessionFactoryRule.getSession();
    Computer c1 = populateComputer("Dell", "D386");
    session.save(c1);
    Computer c2 = populateComputer("IBM", "I386");
    session.save(c2);
    Computer c3 = populateComputer("Dell", "D486");
    session.save(c3);
    Computer c4 = populateComputer("HP", "H586");
    session.save(c4);
    sessionFactoryRule.commit();
    
    @SuppressWarnings("unchecked")
    List<Computer> computers = session.createCriteria(Computer.class).list();

    assertEquals(computers.size(), 4);
    assertTrue(computers.containsAll(Arrays.asList(c1, c2, c3, c4)));
  }

  private Computer populateComputer(String maker, String model) {
    Computer computer = new Computer();
    computer.setMaker(maker);
    computer.setModel(model);
    return computer;
  }
}
