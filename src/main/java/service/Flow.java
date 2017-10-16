package service;

import command.Command;
import command.CommandFactory;
import model.Clock;

public class Flow{
    private final Input input;
    private CommandFactory commandFactory;

    public Flow(Input input, CommandFactory commandFactory) {
        this.input = input;
        this.commandFactory = commandFactory;
    }

    public void start() {
        while(input.hasNextLine()){
            if (input.hasNextLine())
                executeCommand(input.nextLine());
        }
    }

    private void executeCommand(String next) {
        Command command = commandFactory.getCommand(next);
        command.execute(next);
    }
}
