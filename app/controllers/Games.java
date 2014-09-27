package controllers;

import com.google.gson.Gson;
import gamelogic.tictactoe.TicTacToe;
import models.Bot;
import play.libs.F;
import play.mvc.Http;
import play.mvc.WebSocketController;

import static play.mvc.Http.WebSocketEvent.TextFrame;

public class Games extends WebSocketController {

  public static void ticTacToe() {
    Long firstId = null;
    Long secondId = null;

    while (inbound.isOpen() && firstId == null && secondId == null) {
      Http.WebSocketEvent e = await(inbound.nextEvent());

      F.Option<String> match = TextFrame.match(e);
      System.out.println("match.isDefined() = " + match.isDefined());
      /*if (firstId == null && match.isDefined()) {
        firstId = Long.parseLong(match.get());
      }
      if (secondId == null && match.isDefined()) {
        secondId = Long.parseLong(match.get());
      }*/
    }

    System.out.println("firstId = " + firstId);
    Bot firstBot = Bot.findById(firstId);
    Bot secondBot = Bot.findById(secondId);

    System.out.println("firstBot = " + firstBot);
    System.out.println("secondBot = " + secondBot);
//    while (inbound.isOpen()) {
    TicTacToe game = new TicTacToe();
    game.init(firstBot.getPath(), secondBot.getPath());


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
