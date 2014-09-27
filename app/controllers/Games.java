package controllers;

import com.google.gson.Gson;
import gamelogic.tictactoe.TicTacToe;
import play.libs.F;
import play.mvc.Http;
import play.mvc.WebSocketController;

import static play.mvc.Http.WebSocketEvent.TextFrame;

public class Games extends WebSocketController {

  public static void ticTacToe() {

    F.Tuple<Long, Long> ids = new F.Tuple<>(null, null);
    while (inbound.isOpen() && !isFull(ids)) {
      Http.WebSocketEvent e = await(inbound.nextEvent());
      for (String id : TextFrame.match(e)) {
        Long l = Long.parseLong(id);
        ids = intiId(ids, l);
      }
    }

    TicTacToe game = new TicTacToe(ids._1, ids._2);
    TicTacToe.TicTacToeState state;
    do {
      state = game.step();
      try {
        Thread.sleep(600L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      outbound.send(new Gson().toJson(state));
    } while (state.isPlay());

  }

  private static boolean isFull(F.Tuple<Long, Long> tuple) {
    return tuple._1 != null && tuple._2 != null;
  }

  private static F.Tuple<Long, Long> intiId(F.Tuple<Long, Long> prev, Long fromSocket) {
    if (prev._1 == null) {
      return new F.Tuple<>(fromSocket, null);
    }
    return new F.Tuple<>(prev._1, fromSocket);
  }

}
