package tictactoe.utils;

import java.util.Map;
import tictactoe.domain.enums.Difficulty;
import tictactoe.domain.enums.PlayerType;
import tictactoe.domain.strategies.MoveStrategy;
import tictactoe.domain.entities.Player;
import tictactoe.domain.entities.impl.ComputerPlayer;
import tictactoe.domain.entities.impl.HumanPlayer;
import tictactoe.domain.strategies.impl.EasyMoveStrategy;
import tictactoe.domain.strategies.impl.HardMoveStrategy;
import tictactoe.domain.strategies.impl.MediumMoveStrategy;

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
