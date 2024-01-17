package tictactoe.interfaces.Impl.commands;

import tictactoe.interfaces.Command;

public class ExitCommand implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }
}
