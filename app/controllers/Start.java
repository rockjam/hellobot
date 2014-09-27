package controllers;


import models.Bot;
import play.db.jpa.JPABase;
import play.mvc.Controller;

import java.util.List;

public class Start extends Controller {

  public static void begin() {
    List<JPABase> bots = Bot.findAll();
    render(bots);
  }

}
