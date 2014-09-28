import gamelogic.tictactoe.TicTacToe;
import org.junit.Assert;
import org.junit.Test;

public class Hour2 extends Assert {

  @Test
  public void checkLineTest() {
    char[][] field = {
        {'x', 'x', 'o'},
        {'x', 'x', 'x'},
        {'o', 'o', 'o'}
    };

    TicTacToe ticTacToe = new TicTacToe(field, "", "");
    assertFalse(ticTacToe.isWinLine(0, 0, 0, 1));
    assertTrue(ticTacToe.isWinLine(1, 0, 0, 1));
    assertTrue(ticTacToe.isWinLine(2, 0, 0, 1));

    assertFalse(ticTacToe.isWinLine(0, 0, 1, 0));
    assertFalse(ticTacToe.isWinLine(0, 1, 1, 0));
    assertFalse(ticTacToe.isWinLine(0, 2, 1, 0));
  }


  @Test
  public void checkLineTestDiagonals() {
    char[][] field = {
        {'x', 'x', 'o'},
        {'x', 'x', 'x'},
        {'o', 'o', 'x'}
    };
    TicTacToe ticTacToe = new TicTacToe(field, "", "");
    assertFalse(ticTacToe.isWinLine(2, 0, -1, 1));
    assertTrue(ticTacToe.isWinLine(0, 0, 1, 1));
  }

}
