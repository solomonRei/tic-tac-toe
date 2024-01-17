package tictactoe.utils;

import tictactoe.enums.Difficulty;
import tictactoe.enums.PlayerType;
import tictactoe.interfaces.Impl.entities.ComputerPlayer;
import tictactoe.interfaces.Impl.entities.HumanPlayer;
import tictactoe.interfaces.Impl.strategies.EasyMoveStrategy;
import tictactoe.interfaces.Impl.strategies.HardMoveStrategy;
import tictactoe.interfaces.Impl.strategies.MediumMoveStrategy;
import tictactoe.interfaces.MoveStrategy;
import tictactoe.interfaces.Player;

import java.util.HashMap;

class PlayerFactory {

    public static Player createPlayer(HashMap<PlayerType, Difficulty> playerConfig) {
        var playerType = playerConfig.keySet().iterator().next();
        var difficulty = playerConfig.get(playerType);
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

