package tictactoe;


import tictactoe.utils.ConsoleHandler;

public class Main {

    public static void main(String[] args) {
        while (true) {
            String commandInput = ConsoleHandler.promptUserForCommand();
            try {
                String[] tokens = commandInput.trim().split("\\s+");
                var command = ConsoleHandler.parseCommand(tokens);
                command.execute();
            } catch (IllegalArgumentException e) {
                ConsoleHandler.printMessage(ConsoleHandler.ERROR_INVALID_COMMAND);
            }
        }
    }

}
