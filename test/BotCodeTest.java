import gamelogic.tictactoe.Strategy;
import gamelogic.tictactoe.StrategyCreation;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BotCodeTest extends Assert {


  @Test
  public void Minimax() {
    String[][] field = {
        {"-", "-", "-"},
        {"-", "-", "-"},
        {"-", "-", "-"}
    };
    Strategy strategy = StrategyCreation.createBot("bots/TicTacToe/Minimax.js");
    assertThat(strategy, notNullValue());

    int[] move = strategy.makeMove(field, "x");
    int[] expected = {1, 1};
    assertThat(move, is(expected));
  }

  @Test
  public void SimpleTableLookup() {
    String[][] field = {
        {"-", "-", "-"},
        {"-", "-", "-"},
        {"-", "-", "-"}
    };
    Strategy strategy = StrategyCreation.createBot("bots/TicTacToe/SimpleTableLookup.js");

    int[] move = strategy.makeMove(field, "x");
    int[] expected = {1, 1};
    assertThat(move, is(expected));
  }
}
