package controllers;

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

  public static void addBot(String botName, String bot) throws IOException {

    File botFile = new File("bots/TicTacToe/" + botName + ".js");

    //TODO: Сделать проверку, что в botName только английские буквы и цифры
    if(!botFile.exists()) {
      try (OutputStreamWriter writer =
               new OutputStreamWriter(
                   new FileOutputStream(botFile, true))) {
        BufferedWriter out = new BufferedWriter(writer);

        out.write(bot);
        out.flush();
        out.close();

      } catch (IOException ex) {
        System.err.println(ex);
      }
      Application.index();
    } else {
      //TODO: Вывести сообщение, что файл с таким именем уже есть
      addBotForm();
    }
  }
}
