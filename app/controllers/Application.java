package controllers;

import models.People;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void registration() {render();}

    public static void saveUser(People user) {
      user.save();
      index();
    }

    public static void login() {
      render();
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