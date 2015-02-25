package ca.zl.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Computer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "computer_id")
  private Long id;

  private String maker;

  private String model;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMaker() {
    return maker;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Computer)) {
      return false;
    }

    Computer that = (Computer) o;

    return Objects.equals(this.id, that.id) && Objects.equals(this.maker, that.maker)
        && Objects.equals(this.model, that.model);

  }

  @Override
  public int hashCode() {
    return Objects.hash(id, maker, model);
  }
}
