package controllers;

import models.People;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void registration() {render();}

    public static void saveUser(People user) {
      user.save();
      renderTemplate("@login");
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