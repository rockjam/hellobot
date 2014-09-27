import gamelogic.tictactoe.Bot;
import gamelogic.tictactoe.TicTacToe;
import org.junit.Assert;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static org.hamcrest.CoreMatchers.is;

public class BotTest extends Assert {

  @Test
  public void scriptEngineTest() throws ScriptException {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    engine.eval("function sum(a,b) {return a+b}");

    Invocable invocable = (Invocable) engine;

    Adder adder = invocable.getInterface(Adder.class);
    System.out.println(adder.sum(1, 2));
  }

  @Test
  public void bot1_test() throws Exception {
    char[][] field = {
        {'-', '-', '-'},
        {'-', '-', '-'},
        {'-', '-', '-'}
    };
    Bot dummy = new TicTacToe().createBot("dummyBot.js");
    int[] move = dummy.makeMove(field, 'x');
    int[] expected = {0, 0};
    assertThat(move, is(expected));
  }

  public interface Adder {
    int sum(int a, int b);
  }

  @Test
  public void checkLineTest() {
    char[][] field = {
        {'x', 'x', 'o'},
        {'x', 'x', 'x'},
        {'o', 'o', 'o'}
    };
    TicTacToe ticTacToe = new TicTacToe(field);
    assertFalse(ticTacToe.isLineCompleted(0, 0, 0, 1));
    assertTrue(ticTacToe.isLineCompleted(1, 0, 0, 1));
    assertTrue(ticTacToe.isLineCompleted(2, 0, 0, 1));

    assertFalse(ticTacToe.isLineCompleted(0, 0, 1, 0));
    assertFalse(ticTacToe.isLineCompleted(0, 1, 1, 0));
    assertFalse(ticTacToe.isLineCompleted(0, 2, 1, 0));
  }


  @Test
  public void checkLineTestDiagonals() {
    char[][] field = {
        {'x', 'x', 'o'},
        {'x', 'x', 'x'},
        {'o', 'o', 'x'}
    };
    TicTacToe ticTacToe = new TicTacToe(field);
    assertFalse(ticTacToe.isLineCompleted(2, 0, -1, 1));
    assertTrue(ticTacToe.isLineCompleted(0, 0, 1, 1));
  }
}
