package gamelogic.tictactoe;

import gamelogic.Game;
import play.libs.F;

public class TicTacToe implements Game {

  enum BotStatus {
    WIN,
    LOSE,
    PLAYING
  }

  enum GameState {
    PLAY,
    OVER
  }

  private static final int FIELD_SIZE = 3;
  private static final int MAX_INDEX = FIELD_SIZE - 1;

  private final char[][] field;
  private Player first;
  private Player second;

  private F.Tuple<Player, Player> turns;

  public TicTacToe(char[][] field, Long botId1, Long botId2) {
    this.field = field;
    this.first = new Player('x', botId1);
    this.second = new Player('o', botId2);
    this.turns = new F.T2<>(first, second);
  }

  public TicTacToe(Long botId1, Long botId2) {
    this(new char[][]{
        {'-', '-', '-'},
        {'-', '-', '-'},
        {'-', '-', '-'}
    }, botId1, botId2);
  }

  @Deprecated
  public void init(String path1, String path2) {
    first = new Player('x', path1);
    second = new Player('o', path2);
  }

  public TicTacToeState step() {
    if (nextStep() != BotStatus.PLAYING) {
      return new TicTacToeState(GameState.OVER, field, turns._2.bot().getName());
    }
    return new TicTacToeState(GameState.PLAY, field);
  }

  public BotStatus nextStep() {
    Player current = turns._1;

    String[][] jsField = new String[FIELD_SIZE][FIELD_SIZE];
    for (int row = 0; row < field.length; ++row) {
      for (int col = 0; col < field[row].length; col++) {
        jsField[row][col] = new String(String.valueOf(field[row][col]));
      }
    }

    int[] step = current.strategy().makeMove(jsField, String.valueOf(current.side()));
    if (!canMakeMove(step)) {
      return BotStatus.LOSE;
    }
    applyStep(step, current.side());
    turns = new F.T2<>(turns._2, turns._1);
    return gameStatus();
  }

  private BotStatus gameStatus() {
    if (gameOver()) {
      return BotStatus.WIN;
    }
    return BotStatus.PLAYING;
  }

  public boolean gameOver() {
    return isLineCompleted(1, 0, 0, 1)
        || isLineCompleted(0, 0, 0, 1)
        || isLineCompleted(2, 0, 0, 1)
        || isLineCompleted(0, 0, 1, 0)
        || isLineCompleted(0, 1, 1, 0)
        || isLineCompleted(0, 2, 1, 0)
        || isLineCompleted(2, 0, -1, 1)
        || isLineCompleted(0, 0, 1, 1);
  }

  public boolean isLineCompleted(int x, int y, int dx, int dy) {
    boolean result = true;

    if (field[x][y] == '-') return false;

    int i = 0;
    while (result && i < FIELD_SIZE - 1) {
      x += dx;
      y += dy;
      result = field[x][y] == field[(x - dx)][(y - dy)];
      i++;
    }
    return result;
  }

  private void applyStep(int[] step, char symbol) {
    int x = step[0];
    int y = step[1];
    field[x][y] = symbol;
  }

  private boolean canMakeMove(int[] step) {
    int x = step[0];
    int y = step[1];
    return !(x > MAX_INDEX || x < 0 || y > MAX_INDEX || y < 0 || field[x][y] != '-');
  }

  public final class TicTacToeState {
    private final GameState state;
    private final char[][] field;
    private final String winner;

    public TicTacToeState(GameState state, char[][] field) {
      this(state, field, null);
    }

    public TicTacToeState(GameState state, char[][] field, String winner) {
      this.state = state;
      this.field = field;
      this.winner = winner;
    }

    public boolean isPlay() {
      return state == GameState.PLAY;
    }

    public char[][] getField() {
      return field;
    }

    public String getWinner() {
      return winner;
    }
  }

}
