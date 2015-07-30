package ca.zl;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Rule;
import org.junit.Test;

import ca.zl.domain.Employee;

public class FormulaTest {
  @Rule
  public SessionFactoryRule sessionFactoryRule = new SessionFactoryRule();

  @Test
  public void afterSave() {
    Session session = sessionFactoryRule.getSession();
    Employee e1 = populateEmployee("John", "Smith");
    session.save(e1);
    Employee employee = (Employee) session.load(Employee.class, e1.getId());
    assertEquals("Smith", employee.getLastName());
    assertNull(employee.getFullName());
  }

  @Test
  public void afterSaveFlush() {
    Session session = sessionFactoryRule.getSession();
    Employee e1 = populateEmployee("John", "Smith");
    session.save(e1);
    session.flush();
    Employee employee = (Employee) session.load(Employee.class, e1.getId());
    assertEquals("Smith", employee.getLastName());
    assertNull(employee.getFullName());
  }

  @Test
  public void afterSaveCommit() {
    Session session = sessionFactoryRule.getSession();
    Employee e1 = populateEmployee("John", "Smith");
    session.save(e1);
    sessionFactoryRule.commit();
    Employee employee = (Employee) session.load(Employee.class, e1.getId());
    assertEquals("Smith", employee.getLastName());
    assertNull(employee.getFullName());
  }

  @Test
  public void afterSaveRefresh() {
    Session session = sessionFactoryRule.getSession();
    Employee e1 = populateEmployee("John", "Smith");
    session.save(e1);
    session.refresh(e1);
    Employee employee = (Employee) session.load(Employee.class, e1.getId());
    assertEquals("Smith", employee.getLastName());
    assertEquals("John Smith", employee.getFullName());
  }

  @Test
  public void afterSaveRefreshUpdate() {
    Session session = sessionFactoryRule.getSession();
    Employee e1 = populateEmployee("John", "Smith");
    session.save(e1);
    session.refresh(e1);
    e1.setLastName("Black");
    session.save(e1);
    Employee employee = (Employee) session.load(Employee.class, e1.getId());
    assertEquals("Black", employee.getLastName());
    assertEquals("John Smith", employee.getFullName());
  }

  @Test
  public void afterSaveRefreshUpdateRefresh() {
    Session session = sessionFactoryRule.getSession();
    Employee e1 = populateEmployee("John", "Smith");
    session.save(e1);
    session.refresh(e1);
    e1.setLastName("Black");
    session.save(e1);
    session.refresh(e1);
    Employee employee = (Employee) session.load(Employee.class, e1.getId());
    assertEquals("Smith", employee.getLastName());
    assertEquals("John Smith", employee.getFullName());
  }

  @Test
  public void afterSaveUpdateCommit() {
    Session session = sessionFactoryRule.getSession();
    Employee e1 = populateEmployee("John", "Smith");
    e1.setLastName("Black");
    session.save(e1);
    sessionFactoryRule.commit();
    Employee employee = (Employee) session.load(Employee.class, e1.getId());
    assertEquals("Black", employee.getLastName());
    assertNull(employee.getFullName());
  }

  @Test
  public void afterSaveUpdateCommitRefresh() {
    Session session = sessionFactoryRule.getSession();
    Employee e1 = populateEmployee("John", "Smith");
    e1.setLastName("Black");
    session.save(e1);
    sessionFactoryRule.commit();
    session.refresh(e1);
    Employee employee = (Employee) session.load(Employee.class, e1.getId());
    assertEquals("Black", employee.getLastName());
    assertEquals("John Black", employee.getFullName());
  }

  private Employee populateEmployee(String firstName, String lastName) {
    Employee employee = new Employee();
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    return employee;
  }
}
