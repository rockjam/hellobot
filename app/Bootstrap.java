import models.Bot;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import java.io.File;

@OnApplicationStart
public class Bootstrap extends Job<Void> {

  @Override
  public void doJob() throws Exception {
    System.out.println("===========================================");
    if (Bot.count() > 0) {
      return;
    }
    new Bot("Пьяный мастер", String.format("bots%sTicTacToe%s%s", File.separator, File.separator, "DrunkRandomer.js")).save();
    new Bot("Terminator", String.format("bots%sTicTacToe%s%s", File.separator, File.separator, "Minimax.js")).save();
    new Bot("Loser", String.format("bots%sTicTacToe%s%s", File.separator, File.separator, "SimpleTableLookup.js")).save();

  }
}
