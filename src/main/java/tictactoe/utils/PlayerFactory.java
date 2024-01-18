package tictactoe.utils;

import java.util.Map;
import tictactoe.enums.Difficulty;
import tictactoe.enums.PlayerType;
import tictactoe.interfaces.MoveStrategy;
import tictactoe.interfaces.Player;
import tictactoe.interfaces.impl.entities.ComputerPlayer;
import tictactoe.interfaces.impl.entities.HumanPlayer;
import tictactoe.interfaces.impl.strategies.EasyMoveStrategy;
import tictactoe.interfaces.impl.strategies.HardMoveStrategy;
import tictactoe.interfaces.impl.strategies.MediumMoveStrategy;

/** Player factory. */
public final class PlayerFactory {

  /** Private constructor. */
  private PlayerFactory() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Create player.
   *
   * @param playerConfig player configuration
   * @return player
   */
  public static Player createPlayer(final Map<PlayerType, Difficulty> playerConfig) {
    if (playerConfig.isEmpty()) {
      throw new IllegalArgumentException("Player configuration cannot be empty");
    }

    Map.Entry<PlayerType, Difficulty> entry = playerConfig.entrySet().iterator().next();
    var playerType = entry.getKey();
    var difficulty = entry.getValue();
    MoveStrategy strategy;

    if (difficulty != null && playerType == PlayerType.COMPUTER) {
      switch (difficulty) {
        case EASY:
          strategy = new EasyMoveStrategy();
          break;
        case MEDIUM:
          strategy = new MediumMoveStrategy();
          break;
        case HARD:
          strategy = new HardMoveStrategy();
          break;
        default:
          throw new IllegalArgumentException("Unknown difficulty level");
      }
      return new ComputerPlayer(strategy);
    }

    if (playerType == PlayerType.USER) {
      return new HumanPlayer();
    }

    throw new IllegalArgumentException("Unknown player type");
  }
}
