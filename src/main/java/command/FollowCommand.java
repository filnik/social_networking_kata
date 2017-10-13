package command;

import model.Clock;
import model.User;
import model.UserFactory;
import service.Output;

public class FollowCommand extends Command {

    public FollowCommand(Output outputStream, UserFactory userFactory, Clock clock) {
        super(outputStream, userFactory, clock);
    }

    @Override
    public void execute(String command) {
        String[] users = command.split(" follows ");
        if (userFactory.exists(users)){
            User follower = userFactory.get(users[0]);
            User followee = userFactory.get(users[1]);
            follower.follows(followee);
        }
    }

    @Override
    public boolean checkCondition(String command) {
        return simpleCheck(command, " follows ");
    }
}