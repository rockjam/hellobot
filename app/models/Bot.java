package models;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "bot_seq", sequenceName = "bot_seq")
public class Bot extends GenericModel {

  @Id
  @GeneratedValue(generator = "bot_seq")
  private Long id;

  @Required
  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String path;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
