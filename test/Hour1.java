import gamelogic.tictactoe.Strategy;
import gamelogic.tictactoe.StrategyCreation;
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

public class Hour1 extends Assert {

  public interface Adder {
    int sum(int a, int b);
  }

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
    String[][] field = {
        {"-", "-", "-"},
        {"-", "-", "-"},
        {"-", "-", "-"}
    };

    Writer writer = new OutputStreamWriter(System.out);
    Writer errWriter = new OutputStreamWriter(System.err);
    Strategy dummy = StrategyCreation.createBot("dummyBot.js", writer, errWriter);

    int[] move = dummy.makeMove(field, "x");
    int[] expected = {0, 0};
    assertThat(move, is(expected));
  }
}
