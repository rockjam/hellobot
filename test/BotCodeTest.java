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

public class BotCodeTest extends Assert {


  @Test
  public void Minimax(){
      char[][] field = {
              {'-', '-', '-'},
              {'-', '-', '-'},
              {'-', '-', '-'}
      };
      Bot bot = BotCreation.createBot("bots/TicTacToe/Minimax.js");

      int[] move = bot.makeMove(field, 'x');
      int[] expected = {1, 1};
      assertThat(move, is(expected));
  }
}
