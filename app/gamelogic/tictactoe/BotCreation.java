package gamelogic.tictactoe;

import javax.script.*;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class BotCreation {

  private static String getPathById(Long id) {
    return models.Bot.<models.Bot>findById(id).getPath();
  }

  public static Bot createBot(Long id,  Writer writer, Writer errWriter) {
    return createBot(getPathById(id), writer, errWriter);
  }

  public static Bot createBot(String path, Writer writer, Writer errWriter) {
    try (FileReader reader = new FileReader(path)) {
      final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
      engine.getContext().setWriter(writer);
      engine.getContext().setErrorWriter(errWriter);
      engine.eval(reader);
      Invocable invocable = (Invocable) engine;
      return invocable.getInterface(Bot.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
