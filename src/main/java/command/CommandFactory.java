package command;

import model.Clock;
import model.UserFactory;
import service.Output;

import java.util.ArrayList;

public class CommandFactory {

    private final ArrayList<Command> commands = new ArrayList<>();

    public CommandFactory(Clock clock, Output outputStream){
        UserFactory userFactory = new UserFactory();
        commands.add(new PostCommand(outputStream, userFactory, clock));
        commands.add(new ReadCommand(outputStream, userFactory, clock));
        commands.add(new FollowCommand(outputStream, userFactory, clock));
        commands.add(new WallCommand(outputStream, userFactory, clock));
    }

    public Command getCommand(String commandGiven) {
        for (Command command : commands){
            if (command.checkCondition(commandGiven))
                return command;
        }
        return null;
    }
}
