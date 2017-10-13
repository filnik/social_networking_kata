package service;

import command.Command;
import command.CommandFactory;
import model.Clock;

public class Flow{
    private final Input inputStream;
    private CommandFactory commandFactory;

    public Flow(Clock clock, Input inputStream, Output outputStream) {
        this.inputStream = inputStream;
        this.commandFactory = new CommandFactory(clock, outputStream);
    }

    public void start() {
        while(inputStream.hasNextLine()){
            if (inputStream.hasNextLine())
                executeCommand(inputStream.nextLine());
        }
    }

    private void executeCommand(String next) {
        System.out.println(next);
        Command command = commandFactory.getCommand(next);
        command.execute(next);
    }
}
