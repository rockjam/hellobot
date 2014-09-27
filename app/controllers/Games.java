package controllers;

import com.google.gson.Gson;
import gamelogic.tictactoe.TicTacToe;
import play.mvc.WebSocketController;

public class Games extends WebSocketController {

  public static void ticTacToe() {
//    while (inbound.isOpen()) {
    TicTacToe game = new TicTacToe();
    game.init("testBot1.js", "testBot2.js");


    TicTacToe.TicTacToeState state = null;
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
