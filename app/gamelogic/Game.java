package gamelogic;

public interface Game {

  enum BotStatus {
    WIN("Игра окончена! Победитель: "),
    LOSE("Игра закончилась преждевременно"),
    TIE("Ничья"),
    PLAY("");

    public String message;

    BotStatus(String message) {
      this.message = message;
    }
  }
}
