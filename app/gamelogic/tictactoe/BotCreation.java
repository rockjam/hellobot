package gamelogic.tictactoe;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

public class BotCreation {
  public static Bot createBot(String path) {
    try (FileReader reader = new FileReader(path)) {
      final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
      engine.eval(reader);
      Invocable invocable = (Invocable) engine;
      return invocable.getInterface(Bot.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}