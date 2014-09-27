package controllers;

import play.mvc.Http;
import play.mvc.WebSocketController;

public class Games extends WebSocketController {

  public static void ticTacToe() {
    while (inbound.isOpen()) {
      Http.WebSocketEvent e = await(inbound.nextEvent());
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
    }

  }
}
