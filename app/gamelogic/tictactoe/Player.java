package gamelogic.tictactoe;

import java.io.StringWriter;
import java.io.Writer;

public class Player {

  private final char side;
  private final Bot bot;
  private final StringWriter logWriter;

  public Player(char side, String path) {
    this.side = side;
    this.logWriter = new StringWriter();
    this.bot = BotCreation.createBot(path, this.logWriter, this.logWriter);

  }

  public Player(char side, Long id) {
    this.side = side;
    this.logWriter = new StringWriter();
    this.bot = BotCreation.createBot(id, this.logWriter, this.logWriter);
  }

  public Bot bot() {
    return bot;
  }

  public char side() {
    return side;
  }

  public String getLog() { return logWriter.toString(); }

}
