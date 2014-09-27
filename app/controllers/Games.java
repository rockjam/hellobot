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
    boolean read = true;
    while (inbound.isOpen() && read) {
      Http.WebSocketEvent e = await(inbound.nextEvent());
      for (String id : TextFrame.match(e)) {
        Long l = Long.parseLong(id);
        if (ids._1 != null && ids._2 == null) {
          ids = new F.Tuple<>(ids._1, l);
          read = false;
        }
        if (ids._1 == null) {
          ids = new F.Tuple<>(l, null);
        }
      }
    }

    TicTacToe game = new TicTacToe(ids._1, ids._2);
    TicTacToe.TicTacToeState state;
    do {
      state = game.step();
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      outbound.send(new Gson().toJson(state));
    } while (state.isPlay());

//      Http.WebSocketEvent e = await(inbound.nextEvent());


      /*for(String quit: TextFrame.and(Equals("quit")).match(e)) {
        outbound.send("Bye!");
        disconnect();
      }


      for(String message: TextFrame.match(e)) {
        while(true){outbound.send("Echo: %s", message);}
      }

      for(Http.WebSocketClose closed: SocketClosed.match(e)) {
        Logger.info("Socket closed!");
      }*/
//    }

  }
}
