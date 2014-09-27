package gamelogic.tictactoe;

public class GameExecutor {

  public void playGame(TicTacToe game) {

    for (TicTacToe.TicTacToeState state = game.step(); state.isPlay(); state = game.step()) {
      System.out.println("fuck you");
      //ws.write(state)
    }

  }

}
