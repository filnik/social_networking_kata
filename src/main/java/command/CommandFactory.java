package command;

import model.Clock;
import model.UserRepository;
import service.Output;

import java.util.ArrayList;

public class CommandFactory {

    private final ArrayList<Command> commands = new ArrayList<>();

    public CommandFactory(Clock clock, Output output){
        UserRepository userRepository = new UserRepository();
        initCommands(clock, output, userRepository);
    }

    private void initCommands(Clock clock, Output output, UserRepository userRepository) {
        commands.add(new PostCommand(output, userRepository, clock));
        commands.add(new ReadCommand(output, userRepository, clock));
        commands.add(new FollowCommand(output, userRepository, clock));
        commands.add(new WallCommand(output, userRepository, clock));
    }

    public Command getCommand(String commandGiven) {
        for (Command command : commands){
            if (command.checkCondition(commandGiven))
                return command;
        }
        return null;
    }
}
