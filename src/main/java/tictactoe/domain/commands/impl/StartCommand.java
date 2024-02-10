package tictactoe.domain.commands.impl;

import java.util.HashMap;
import java.util.Map;
import tictactoe.domain.enums.Difficulty;
import tictactoe.domain.enums.PlayerType;
import tictactoe.domain.commands.Command;
import tictactoe.interfaces.impl.GameBoardImpl;
import tictactoe.utils.Game;

/** StartCommand class, used for starting the game. */
public class StartCommand implements Command {

  /** Player 1 type and difficulty setting. */
  private final Map<PlayerType, Difficulty> player1Type;

  /** Player 2 type and difficulty setting. */
  private final Map<PlayerType, Difficulty> player2Type;

  /**
   * Constructor to create a start command with specified player types and difficulties.
   *
   * @param p1Type the type and difficulty setting for player 1
   * @param p2Type the type and difficulty setting for player 2
   */
  public StartCommand(
          final Map<PlayerType, Difficulty> p1Type,
          final Map<PlayerType, Difficulty> p2Type) {
    this.player1Type = new HashMap<>(p1Type); // Make a defensive copy
    this.player2Type = new HashMap<>(p2Type); // Make a defensive copy
  }

  /** Execute and start the game. */
  @Override
  public void execute() {
    var game = new Game(new GameBoardImpl(), player1Type, player2Type);
    game.start();
  }
}
