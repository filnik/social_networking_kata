package service;

import command.Command;
import command.CommandFactory;
import model.*;

public class MemoryFlow implements Flow {
    private final Input inputStream;
    private CommandFactory commandFactory;

    public MemoryFlow(Clock clock, Input inputStream, Output outputStream) {
        this.inputStream = inputStream;
        this.commandFactory = new CommandFactory(clock, outputStream);
    }

    public void start() {
        while(inputStream.hasNext()){
            if (inputStream.hasNext())
                executeCommand(inputStream.next());
        }
    }

    private void executeCommand(String next) {
        Command command = commandFactory.getCommand(next);
        command.execute(next);
    }
}
