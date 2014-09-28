package gamelogic.tictactoe;


public interface Strategy {
  public int[] makeMove(String[][] field, String symbol);
}
