import gamelogic.tictactoe.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.OutputStreamWriter;
import java.io.Writer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BotCodeTest extends Assert {


  @Test
  public void Minimax_OneTurn(){
    String[][] field = {
            {"-", "-", "-"},
            {"-", "-", "-"},
            {"-", "-", "-"}
    };

    Writer writer = new OutputStreamWriter(System.out);
    Writer errWriter = new OutputStreamWriter(System.err);
    Strategy bot = StrategyCreation.createBot("bots/TicTacToe/Minimax.js", writer, errWriter);
    assertThat(bot, notNullValue());

    int[] move = bot.makeMove(field, "x");
    int[] expected = {1, 1};
    assertThat(move, is(expected));
  }

  @Test
  public void Minimax_VsMinimax(){

    TicTacToe game = new TicTacToe(TicTacToe.getEmptyField(), "bots/TicTacToe/Minimax.js", "bots/TicTacToe/Minimax.js");

    int turnsToDo = 10;
    while(turnsToDo-- > 0 && !game.gameOver())
    {
        game.step();
        assertThat(game.gameOver(), is(false));
    }
  }

  @Test
  public void SimpleTableLookup(){
    String[][] field = {
            {"-", "-", "-"},
            {"-", "-", "-"},
            {"-", "-", "-"}
    };
    Writer writer = new OutputStreamWriter(System.out);
    Writer errWriter = new OutputStreamWriter(System.err);
    Strategy bot = StrategyCreation.createBot("bots/TicTacToe/SimpleTableLookup.js", writer, errWriter);

    int[] move = bot.makeMove(field, "x");
    int[] expected = {1, 1};
    assertThat(move, is(expected));
  }
}
