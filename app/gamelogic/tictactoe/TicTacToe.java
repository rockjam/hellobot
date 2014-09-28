package gamelogic.tictactoe;

import gamelogic.Game;
import play.libs.F;

import java.util.EnumSet;

public class TicTacToe implements Game {

  private static final int FIELD_SIZE = 3;
  private static final int MAX_INDEX = FIELD_SIZE - 1;

  private final char[][] field;
  private Player first;
  private Player second;//это наш враг

  private F.Tuple<Player, Player> turns;

  public TicTacToe(char[][] field, Long botId1, Long botId2) {
    this.field = field;
    this.first = new Player('x', botId1);
    this.second = new Player('o', botId2);
    this.turns = new F.T2<>(first, second);
  }

  public TicTacToe(char[][] field, String path1, String path2) {
    this.field = field;
    this.first = new Player('x', path1);
    this.second = new Player('o', path2);
    this.turns = new F.T2<>(first, second);
  }

  public TicTacToe(Long botId1, Long botId2) {
    this(getEmptyField(), botId1, botId2);
  }

  public static char[][] getEmptyField() {
    return new char[][]{
        {'-', '-', '-'},
        {'-', '-', '-'},
        {'-', '-', '-'}
    };
  }

  public TicTacToeState step() {
    final BotStatus state = nextStep();
    if (EnumSet.of(BotStatus.LOSE, BotStatus.TIE, BotStatus.PLAY).contains(state)) {
      return new TicTacToeState(state, field, first.getLog(), second.getLog());
    }
    return new TicTacToeState(state, field, turns._2.bot().getName(), first.getLog(), second.getLog());
  }

  public BotStatus nextStep() {
    Player current = turns._1;

    String[][] jsField = new String[FIELD_SIZE][FIELD_SIZE];
    for (int row = 0; row < field.length; ++row) {
      for (int col = 0; col < field[row].length; col++) {
        jsField[row][col] = String.valueOf(field[row][col]);
      }
    }

    int[] step = current.strategy().makeMove(jsField, String.valueOf(current.side()));
    current.getLog().toString();

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
    private final String winner;
    private final String firstLog;
    private final String secondLog;

    public TicTacToeState(BotStatus state, char[][] field, String firstLog, String secondLog) {
      this(state, field, null, firstLog, secondLog);
    }

    public TicTacToeState(BotStatus state, char[][] field, String winner, String firstLog, String secondLog) {
      this.state = state;
      this.message = state.message;
      this.field = field;
      this.winner = winner;
      this.firstLog = firstLog;
      this.secondLog = secondLog;
    }

    public boolean isPlay() {
      return state == BotStatus.PLAY;
    }

    public char[][] getField() {
      return field;
    }
  }

}
