package controllers;

import models.People;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;

public class Application extends Controller {

  public static void index(Long firstId, Long secondId) {
    System.out.println("firstId = [" + firstId + "], secondId = [" + secondId + "]");
    render(firstId, secondId);
  }

  
    public static void registration() {render();}

    public static void saveUser(@Valid People user) {
      if (Validation.hasErrors()) {
        renderTemplate("@registration", user);
      }
      user._save();
      renderTemplate("@login", "Вы успешно зарегистрировались");
    }

    public static void login() {
      renderTemplate("@login");
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

  public static void main() {
    render();
  }


}