package controllers;

import play.mvc.Controller;

public class Application extends Controller {

  public static void index(Long firstId, Long secondId) {
    System.out.println("firstId = [" + firstId + "], secondId = [" + secondId + "]");
    render(firstId, secondId);
  }

  public static void registration() {
    render();
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