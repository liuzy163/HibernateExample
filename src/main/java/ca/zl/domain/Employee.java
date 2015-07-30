package ca.zl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "employee_id")
  private long id;
  private String firstName;
  private String lastName;
  // @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
  // @Cascade(CascadeType.SAVE_UPDATE)
  // @Fetch(FetchMode.SELECT)
  // private Set<Computer> computers;

  @Formula("CONCAT(firstName, ' ', lastName)")
  private String fullName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public String toString() {
    return "Employee: id=" + id + " name=" + fullName;
  }
}
