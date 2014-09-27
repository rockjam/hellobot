package gamelogic.tictactoe;

import gamelogic.Game;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TicTacToe implements Game {

  private final char[][] field = {
      {'-', '-', '-'},
      {'-', '-', '-'},
      {'-', '-', '-'}
  };

  private Bot first;
  private Bot second;

  public void execute(String path1, String path2) throws FileNotFoundException, ScriptException {
    first = createBot(path1);
    second = createBot(path2);


  }

  public Bot createBot(String path) {
    try (FileReader reader = new FileReader(path)) {
      final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
      engine.eval(reader);
      Invocable invocable = (Invocable) engine;
      return invocable.getInterface(Bot.class);
    } catch (Exception e) {
      System.out.println("+++++++++========++++++===++++===+++==+++===++===+");
      System.out.println(e.getMessage());
      return null;
    }
  }
}
