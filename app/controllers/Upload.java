package controllers;

import play.mvc.Controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Upload extends Controller {

  public static void addBotForm() {
    render();
  }

  public static void addBot(String botName, String bot) throws IOException {

    try(OutputStreamWriter writer =
            new OutputStreamWriter(
                new FileOutputStream("bots/TicTacToe/" + botName + ".js", true)))  {
      BufferedWriter out = new BufferedWriter(writer);

      out.write(bot);
      out.flush();
      out.close();

    }
    catch(IOException ex)
    {
      System.err.println(ex);
    }
  }
}
