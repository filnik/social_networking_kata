package service;

import command.Command;
import command.CommandFactory;
import model.Clock;

import java.util.Iterator;

public class MemoryFlow implements Flow{
    private final Input inputStream;
    private CommandFactory commandFactory;

    public MemoryFlow(Clock clock, Input inputStream, Output outputStream) {
        this.inputStream = inputStream;
        this.commandFactory = new CommandFactory(clock, outputStream);
    }

    public void start() {
        while(inputStream.hasNext()){
            if (inputStream.hasNext())
                executeCommand(inputStream.nextLine());
        }
    }

    private void executeCommand(String next) {
        System.out.println(next);
        Command command = commandFactory.getCommand(next);
        command.execute(next);
    }
}
