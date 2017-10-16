package command;

import model.Clock;
import model.User;
import model.UserRepository;
import service.Output;

public class PostCommand extends Command {

    public PostCommand(Output output, UserRepository userRepository, Clock clock) {
        super(output, userRepository, clock);
    }

    @Override
    public void execute(String command) {
        String[] split = command.split(" -> ");
        final String username = split[0];
        final String message = split[1];
        User user;
        if (userRepository.exists(username)) {
            user = userRepository.load(username);
        } else {
            user = new User(username, clock);
            userRepository.save(user);
        }
        user.addMessage(message);
    }

    @Override
    public boolean checkCondition(String command) {
        if (!command.contains(" -> ")){
            return false;
        }
        String[] split = command.split(" -> ");
        final String username = split[0];
        return !username.contains(" ");
    }
}
