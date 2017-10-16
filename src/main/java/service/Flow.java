package service;

import command.Command;
import command.CommandFactory;
import model.Clock;

public class Flow{
    private final Input inputStream;
    private CommandFactory commandFactory;

    public Flow(Input inputStream, CommandFactory commandFactory) {
        this.inputStream = inputStream;
        this.commandFactory = commandFactory;
    }

    public void start() {
        while(inputStream.hasNextLine()){
            if (inputStream.hasNextLine())
                executeCommand(inputStream.nextLine());
        }
    }

    private void executeCommand(String next) {
        Command command = commandFactory.getCommand(next);
        command.execute(next);
    }
}
