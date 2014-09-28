import gamelogic.tictactoe.TicTacToe;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class Hour3 extends Assert {

  @Test
  public void game_over_1() {
    char[][] field = {
        {'x', '-', 'o'},
        {'x', 'x', 'x'},
        {'o', 'o', '-'}
    };

    TicTacToe game = new TicTacToe(field, "", "");
    assertThat(game.gameOver(), is(true));
  }


  @Test
  public void game_over_2() {
    char[][] field = {
        {'x', '-', 'o'},
        {'o', 'x', 'o'},
        {'-', '-', 'x'}
    };

    TicTacToe game = new TicTacToe(field, "", "");
    assertThat(game.gameOver(), is(true));
  }

  @Test
  public void game_over_3() {
    char[][] field = {
        {'-', '-', 'o'},
        {'o', 'x', 'o'},
        {'-', '-', 'x'}
    };

    TicTacToe game = new TicTacToe(field, "", "");
    assertThat(game.gameOver(), is(false));
  }

  @Test
  public void new_field_game_is_not_over() {
    char[][] field = {
        {'-', '-', '-'},
        {'-', '-', '-'},
        {'-', '-', '-'}
    };

    TicTacToe game = new TicTacToe(field, "", "");
    assertThat(game.gameOver(), is(false));
  }

}
