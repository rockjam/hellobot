package gamelogic.tictactoe;

import java.io.StringWriter;
import java.io.Writer;
import models.Bot;

public class Player {
  
  private final Bot bot;
  private final char side;
  private final Strategy strategy;
  private final StringWriter logWriter;

  @Deprecated
  public Player(char side, String path) {
    this.side = side;
    this.logWriter = new StringWriter();
    this.bot = null;
    this.strategy = StrategyCreation.createBot(path, this.logWriter, this.logWriter);
  }

  public Player(char side, Long id) {
    this.side = side;
    this.logWriter = new StringWriter();
    this.bot = Bot.findById(id);
    this.strategy = StrategyCreation.createBot(bot.getPath(), this.logWriter, this.logWriter);
  }

  public Strategy strategy() {
    return strategy;
  }

  public char side() {
    return side;
  }

  public String getLog() { return logWriter.toString(); }

  public Bot bot() {
    return bot;
  }
}