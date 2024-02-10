package tictactoe.domain.commands.impl;

import tictactoe.Main;
import tictactoe.domain.commands.Command;

/** Exit command. */
public class ExitCommand implements Command {

  /** Exit command execution. */
  @Override
  public void execute() {
    Main.requestExit();
  }
}
