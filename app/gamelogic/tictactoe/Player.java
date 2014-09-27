package gamelogic.tictactoe;

public class Player {

  private final char side;
  private final Bot bot;

  public Player(char side, String path) {
    this.side = side;
    this.bot = BotCreation.createBot(path);
  }

  public Player(char side, Long id) {
    this.side = side;
    this.bot = BotCreation.createBot(id);
  }

  public Bot bot() {
    return bot;
  }

  public char side() {
    return side;
  }

}
