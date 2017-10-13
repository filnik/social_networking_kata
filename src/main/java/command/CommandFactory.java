package command;

import model.Clock;
import model.UserRepository;
import service.Output;

import java.util.ArrayList;

public class CommandFactory {

    private final ArrayList<Command> commands = new ArrayList<>();

    public CommandFactory(Clock clock, Output outputStream){
        UserRepository userRepository = new UserRepository();
        initCommands(clock, outputStream, userRepository);
    }

    private void initCommands(Clock clock, Output outputStream, UserRepository userRepository) {
        commands.add(new PostCommand(outputStream, userRepository, clock));
        commands.add(new ReadCommand(outputStream, userRepository, clock));
        commands.add(new FollowCommand(outputStream, userRepository, clock));
        commands.add(new WallCommand(outputStream, userRepository, clock));
    }

    public Command getCommand(String commandGiven) {
        for (Command command : commands){
            if (command.checkCondition(commandGiven))
                return command;
        }
        return null;
    }
}
