package gamelogic.tictactoe;

import gamelogic.Game;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TicTacToe implements Game {

  enum BotStatus {
    WIN, LOSE, PLAYING
  }

  private static final int FIELD_SIZE = 3;
  private static final int MAX_INDEX = FIELD_SIZE - 1;

  private final char[][] field;
  private Bot first;
  private Bot second;

  public TicTacToe(char[][] field) {
    this.field = field;
  }

  public TicTacToe() {
    this.field = new char[][]{{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
  }

  public void execute(String path1, String path2) throws FileNotFoundException, ScriptException {
    first = createBot(path1);
    second = createBot(path2);
  }

  public BotStatus nextStep(Bot bot) {
    int[] step = bot.makeMove(field, 'x');
    if (!canMakeMove(step)) {
      return BotStatus.LOSE;
    }

    applyStep(step, 'x');
    return null;
  }


  public boolean isLineCompleted(int x, int y, int dx, int dy) {
    boolean result = true;

    if (field[x][y] == '-') return false;

    int i = 0;
    while (result && i < FIELD_SIZE - 1) {
      x += dx;
      y += dy;
      result = field[x][y] == field[(x - dx)][(y - dy)];
      i++;
    }
    return result;
  }

  private void applyStep(int[] step, char symbol) {
    int x = step[0];
    int y = step[1];
    field[x][y] = symbol;
  }

  private boolean canMakeMove(int[] step) {
    int x = step[0];
    int y = step[1];
    return !(x > MAX_INDEX || x < 0 || y > MAX_INDEX || y < 0 || field[x][y] != '-');
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
