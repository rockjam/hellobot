import gamelogic.tictactoe.Bot;
import gamelogic.tictactoe.BotCreation;
import gamelogic.tictactoe.TicTacToe;
import org.junit.Assert;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.io.OutputStreamWriter;
import java.io.StringWriter;
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
    Bot bot = BotCreation.createBot("bots/TicTacToe/Minimax.js", writer, errWriter);
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

    assertThat(game.gameOver(), is(true));
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
    Bot bot = BotCreation.createBot("bots/TicTacToe/SimpleTableLookup.js", writer, errWriter);

    int[] move = bot.makeMove(field, "x");
    int[] expected = {1, 1};
    assertThat(move, is(expected));
  }
}
