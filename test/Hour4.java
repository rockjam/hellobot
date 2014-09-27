import gamelogic.tictactoe.TicTacToe;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class Hour4 extends Assert {

  @Test
  public void game_over_1() {
    TicTacToe game = new TicTacToe(null, null);
    game.init("testBot1.js", "testBot2.js");

    // step 0 - x
    TicTacToe.TicTacToeState step = game.step();
    char[][] field = {
        {'x', '-', '-'},
        {'-', '-', '-'},
        {'-', '-', '-'}
    };
    assertThat(step.isPlay(), is(true));
    assertThat(step.getField(), is(field));

    // step 0 - o
    step = game.step();
    field = new char[][]{
        {'x', '-', '-'},
        {'-', '-', '-'},
        {'o', '-', '-'}
    };
    assertThat(step.isPlay(), is(true));
    assertThat(step.getField(), is(field));

    // step 1 - x
    step = game.step();
    field = new char[][]{
        {'x', '-', '-'},
        {'x', '-', '-'},
        {'o', '-', '-'}
    };
    assertThat(step.isPlay(), is(true));
    assertThat(step.getField(), is(field));

    // step 1 - o
    step = game.step();
    field = new char[][]{
        {'x', '-', '-'},
        {'x', 'o', '-'},
        {'o', '-', '-'}
    };
    assertThat(step.isPlay(), is(true));
    assertThat(step.getField(), is(field));

    // step 2 - x
    step = game.step();
    field = new char[][]{
        {'x', 'x', '-'},
        {'x', 'o', '-'},
        {'o', '-', '-'}
    };
    assertThat(step.isPlay(), is(true));
    assertThat(step.getField(), is(field));

    // step 2 - o
    step = game.step();
    field = new char[][]{
        {'x', 'x', '-'},
        {'x', 'o', '-'},
        {'o', 'o', '-'}
    };
    assertThat(step.isPlay(), is(true));
    assertThat(step.getField(), is(field));

    // step 3 - x
    step = game.step();
    field = new char[][]{
        {'x', 'x', '-'},
        {'x', 'o', 'x'},
        {'o', 'o', '-'}
    };
    assertThat(step.isPlay(), is(true));
    assertThat(step.getField(), is(field));

    // step 3 - o
    step = game.step();
    field = new char[][]{
        {'x', 'x', 'o'},
        {'x', 'o', 'x'},
        {'o', 'o', '-'}
    };
    assertThat(step.isPlay(), is(false));
    assertThat(step.getField(), is(field));
  }
}
