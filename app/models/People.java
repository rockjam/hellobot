package models;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class People extends GenericModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  public String id;

  @Required
  public String name;

  @Required
  public String pass;

  @Required
  @play.data.validation.Email
  public String mail;
}
