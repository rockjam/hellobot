package controllers;

import models.People;
import play.mvc.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Application extends Controller {

  public static void index(Long firstId, Long secondId) {
    render(firstId, secondId);
  }

  
    public static void registration() {render();}

    public static void saveUser(People user) {
      List<String> names = People.find("name = ?", user.getName()).fetch();
      if (names.isEmpty()) {
        user.save();
        renderTemplate("@login");
      } else {
        //TODO пользователь с таким именем уже существует
      }
    }

    public static void login() {
      renderTemplate("@login");
    }

  public static void signIn(String name, String pass) throws NoSuchAlgorithmException {
    List<People> peopleList = People.find("name = ?", name).fetch();
    if (!peopleList.isEmpty()) {

      MessageDigest md = MessageDigest.getInstance("MD5");
      md.digest(pass.getBytes()).equals(peopleList.get(0).getPass());
    }
  }

  public static void create_bot() {
    render();
  }

  public static void faq() {
    render();
  }

  public static void translation() {
    render();
  }


}