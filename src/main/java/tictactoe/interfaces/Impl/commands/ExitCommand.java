package tictactoe.interfaces.impl.commands;

import tictactoe.Main;
import tictactoe.interfaces.Command;

/** Exit command. */
public class ExitCommand implements Command {

  /** Exit command execution. */
  @Override
  public void execute() {
    Main.requestExit();
  }
}
