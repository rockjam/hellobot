package gamelogic.pong;

import gamelogic.Game;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Pong implements Game {

  public final static Double PLATFORM_HEIGHT = 48D;
  public final static Double PLATFORM_MAX_SPEED = 200D;//скорость точек/секунду
  public final static Double BALL_MAX_SPEED = 220D;

  private final Double width = 640D;
  private final Double height = 480D;

  private final Platform ping;
  private final Platform pong;
  private final Ball ball;

  private static List initialSpeed()
  {
     int direction = (int)(Math.random() * 2);
     Double angle = Math.random()*Math.PI/2 - Math.PI / 4 + Math.PI*direction;
     Double vx = BALL_MAX_SPEED * Math.cos(angle);
     Double vy = BALL_MAX_SPEED * Math.sin(angle);
     return new ArrayList<Double>(2) {{ add(vx); add(vy);}};
  }

  public Pong() {
    this.ping = new Platform(240D, 0D);
    this.pong = new Platform(240D, 0D);

    List pos = new ArrayList<Double>(2) {{ add(width/2); add(height/2);}};
    List v = initialSpeed();
    this.ball = new Ball(pos, v);
  }

  public PongState step()
  {
    return PongState();
  }

  public BotStatus nextStep() {
    Player current = turns._1;

    int[] step = current.strategy().makeMove(jsField, String.valueOf(current.side()));
    current.getLog().toString();

    if (!canMakeMove(step)) {
      return BotStatus.LOSE;
    }
    applyStep(step, current.side());
    turns = new F.T2<>(turns._2, turns._1);
    return botStatus();
  }

  public class Platform {
    public Double pos;
    public Double v;
    public final Double height = PLATFORM_HEIGHT;

    public Platform(Double pos, Double v) {
      this.pos = pos;
      this.v = v;
    }
  }

  public class Ball {
    public List<Double> pos;
    public List<Double> v;

    public Ball(List<Double> pos, List<Double> v) {
      this.pos = pos;
      this.v = v;
    }
  }

  public final class PongState {
    private final BotStatus state;
    private final String message;
    private final char[][] field;
    private final String winner;
    private final String firstLog;
    private final String secondLog;

    public PongState(BotStatus state, char[][] field, String firstLog, String secondLog) {
      this(state, field, null, firstLog, secondLog);
    }

    public PongState(BotStatus state, char[][] field, String winner, String firstLog, String secondLog) {
      this.state = state;
      this.message = state.message;
      this.field = field;
      this.winner = winner;
      this.firstLog = firstLog;
      this.secondLog = secondLog;
    }

    public boolean isPlay() {
      return state == BotStatus.PLAY;
    }

    public char[][] getField() {
      return field;
    }
  }




}
