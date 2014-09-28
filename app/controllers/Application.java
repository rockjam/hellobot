package controllers;

import models.Bot;
import models.People;
import play.Logger;
import play.mvc.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Application extends Controller {

  public static void index() {
    List<Bot> rivals = Bot.findAll();
    render(rivals);
  }

  public static void prepareGame(Bot bot, String sourceCode) {
    String path = bot.getPath() != null ? bot.getPath() : String.format("bots%sTicTacToe%s%s-%d.js", File.separator, File.separator, bot.getName(), System.currentTimeMillis());
    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path))) {
      BufferedWriter out = new BufferedWriter(writer);
      out.write(sourceCode);
      out.flush();
      out.close();

      bot.setPath(path);
      bot._save();
    } catch (IOException ex) {
      bot._delete();
      renderJSON("{\"error\":\"Source code not saved\"}");
      Logger.error(ex, "Save file error");
    }

    renderJSON(new Object[]{bot.getId(), bot.getName()});
  }

  public static void pong() {
    render();
  }

  public static void registration() {
    render();
  }

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

  public static void faq() {
    render();
  }

  public static void translation() {
    render();
  }
}