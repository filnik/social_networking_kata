package command;

import model.Clock;
import model.User;
import model.UserRepository;
import service.Output;

public class FollowCommand extends Command {

    public FollowCommand(Output outputStream, UserRepository userRepository, Clock clock) {
        super(outputStream, userRepository, clock);
    }

    @Override
    public void execute(String command) {
        String[] users = command.split(" follows ");
        if (userRepository.exists(users)){
            User follower = userRepository.load(users[0]);
            User followee = userRepository.load(users[1]);
            follower.follows(followee);
        }
    }

    @Override
    public boolean checkCondition(String command) {
        String[] split = command.split(" follows ");
        return userRepository.exists(split[0]);
    }
}