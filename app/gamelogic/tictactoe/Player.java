package gamelogic.tictactoe;

import models.Bot;

public class Player {
  private final Bot bot;
  private final char side;
  private final Strategy strategy;

  @Deprecated
  public Player(char side, String path) {
    this.side = side;
    this.bot = null;
    this.strategy = StrategyCreation.createBot(path);
  }

  public Player(char side, Long id) {
    this.side = side;
    this.bot = Bot.findById(id);
    this.strategy = StrategyCreation.createBot(bot.getPath());
  }

  public Strategy strategy() {
    return strategy;
  }

  public char side() {
    return side;
  }

  public Bot bot() {
    return bot;
  }
}