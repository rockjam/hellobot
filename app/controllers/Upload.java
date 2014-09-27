package controllers;

import models.Bot;
import play.Logger;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Upload extends Controller {

  public static void addBotForm() {
    render();
  }

  public static void addBot(@Valid Bot bot, String sourceCode) {

    if (Validation.hasErrors()) {
      renderTemplate("@addBotForm", bot, sourceCode);
    }

    bot._save();
    String path = String.format("bots%sTicTacToe%s%s-%d.js", File.separator, File.separator, bot.getName(), bot.getId());

    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path))) {
      BufferedWriter out = new BufferedWriter(writer);
      out.write(sourceCode);
      out.flush();
      out.close();

      bot.setPath(path);
      bot._save();
    } catch (IOException ex) {
      bot._delete();
      Validation.addError("bot", "Не удалсь сохранить исходный тескт");
      renderTemplate("@addBotForm", bot, sourceCode);
      Logger.error(ex, "Save file error");
    }
    Start.begin();
  }
}
