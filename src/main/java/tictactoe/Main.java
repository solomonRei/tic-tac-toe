package tictactoe;

import tictactoe.domain.commands.Command;
import tictactoe.utils.ConsoleHandler;

/** Main class. */
public class Main {

  /** Flag to indicate if the application is running. */
  private static boolean isRunning = true;

  /**
   * Main method.
   *
   * @param args Command line arguments passed to the application.
   */
  public static void main(final String[] args) {
    while (isRunning) {
      String commandInput = ConsoleHandler.promptUserForCommand();
      try {
        String[] tokens = commandInput.trim().split("\\s+");
        Command command = ConsoleHandler.parseCommand(tokens);
        command.execute();
      } catch (IllegalArgumentException e) {
        ConsoleHandler.printMessage(ConsoleHandler.ERROR_INVALID_COMMAND);
      }
    }
    ConsoleHandler.closeScanner();
  }

  /** Requests the application to exit. */
  public static void requestExit() {
    isRunning = false;
  }
}
