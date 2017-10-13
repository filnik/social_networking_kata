package command;

import model.Clock;
import model.User;
import model.UserFactory;
import service.Output;

public class PostCommand extends Command {

    public PostCommand(Output outputStream, UserFactory userFactory, Clock clock) {
        super(outputStream, userFactory, clock);
    }

    @Override
    public void execute(String command) {
        String[] split = command.split(" -> ");
        final String username = split[0];
        final String message = split[1];
        User user;
        if (userFactory.exists(username)) {
            user = userFactory.get(username);
        } else {
            user = new User(username, clock);
            userFactory.put(user);
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
