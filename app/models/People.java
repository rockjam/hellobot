package models;

import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@SequenceGenerator(name = "people", sequenceName = "people")
public class People extends GenericModel {

  @Id
  @GeneratedValue(generator = "people")
  private Long id;

  @Required
  @Column(nullable = false)
  private String name;

  @Required
  @Column(nullable = false)
  @Password
  private String pass;

  @Required
  @Column(nullable = false)
  @play.data.validation.Email
  private String mail;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getPass() throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] result = md.digest(pass.getBytes());
    return result;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }
}
