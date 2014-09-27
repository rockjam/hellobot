package gamelogic.tictactoe;

import gamelogic.Game;
import play.libs.F;

public class TicTacToe implements Game {

  enum BotStatus {
    WIN("Победил один из ботов"),
    LOSE("Игра закончилась преждевременно"),
    TIE("Ничья"),
    PLAY("");

    public String title;

    BotStatus(String title) {
      this.title = title;
    }
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
    final BotStatus state = nextStep();
    return new TicTacToeState(state, state.title, field);
  }

  public BotStatus nextStep() {
    Player current = turns._1;

    String[][] jsField = new String[FIELD_SIZE][FIELD_SIZE];
    for (int row = 0; row < field.length; ++row) {
      for (int col = 0; col < field[row].length; col++) {
        jsField[row][col] = new String(String.valueOf(field[row][col]));
      }
    }

    int[] step = current.bot().makeMove(jsField, String.valueOf(current.side()));
    if (!canMakeMove(step)) {
      return BotStatus.LOSE;
    }
    applyStep(step, current.side());
    turns = new F.T2<>(turns._2, turns._1);
    return botStatus();
  }

  private BotStatus botStatus() {
    if (gameOver()) {
      return BotStatus.WIN;
    }
    if (fieldIsFull()) {
      return BotStatus.TIE;
    }
    return BotStatus.PLAY;
  }

  private boolean fieldIsFull() {
    for (char[] rows : field) {
      for (char cols : rows) {
        if (cols == '-') {
          return false;
        }
      }
    }
    return true;
  }

  public boolean gameOver() {
    return isWinLine(1, 0, 0, 1)
        || isWinLine(0, 0, 0, 1)
        || isWinLine(2, 0, 0, 1)
        || isWinLine(0, 0, 1, 0)
        || isWinLine(0, 1, 1, 0)
        || isWinLine(0, 2, 1, 0)
        || isWinLine(2, 0, -1, 1)
        || isWinLine(0, 0, 1, 1);
  }

  public boolean isWinLine(int x, int y, int dx, int dy) {
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
    private final BotStatus state;
    private final String message;
    private final char[][] field;
    private String winner;

    TicTacToeState(BotStatus state, String message, char[][] field) {
      this.state = state;
      this.message = message;
      this.field = field;
    }

    public boolean isPlay() {
      return state == BotStatus.PLAY;
    }

    public char[][] getField() {
      return field;
    }
  }

}
