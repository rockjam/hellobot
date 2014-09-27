import gamelogic.tictactoe.Bot;
import gamelogic.tictactoe.BotCreation;
import gamelogic.tictactoe.TicTacToe;
import org.junit.Assert;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BotCodeTest extends Assert {


  @Test
  public void Minimax(){
      String[][] field = {
              {"-", "-", "-"},
              {"-", "-", "-"},
              {"-", "-", "-"}
      };
      Bot bot = BotCreation.createBot("bots/TicTacToe/Minimax.js");
      assertThat(bot, notNullValue());

      int[] move = bot.makeMove(field, "x");
      int[] expected = {1, 1};
      assertThat(move, is(expected));
  }

    @Test
    public void SimpleTableLookup(){
        String[][] field = {
                {"-", "-", "-"},
                {"-", "-", "-"},
                {"-", "-", "-"}
        };
        Bot bot = BotCreation.createBot("bots/TicTacToe/SimpleTableLookup.js");

        int[] move = bot.makeMove(field, "x");
        int[] expected = {1, 1};
        assertThat(move, is(expected));
    }
}
