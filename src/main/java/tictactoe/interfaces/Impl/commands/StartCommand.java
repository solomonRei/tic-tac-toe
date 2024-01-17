package tictactoe.interfaces.Impl.commands;

import tictactoe.enums.Difficulty;
import tictactoe.enums.PlayerType;
import tictactoe.interfaces.Command;
import tictactoe.interfaces.Impl.GameBoard;
import tictactoe.utils.Game;

import java.util.HashMap;

public class StartCommand implements Command {

    private final HashMap<PlayerType, Difficulty> player1Type;

    private final HashMap<PlayerType, Difficulty> player2Type;

    public StartCommand(HashMap<PlayerType, Difficulty> player1Type, HashMap<PlayerType, Difficulty> player2Type) {
        this.player1Type = player1Type;
        this.player2Type = player2Type;
    }

    @Override
    public void execute() {
        var game = new Game(new GameBoard(), player1Type, player2Type);
        game.start();
    }
}
