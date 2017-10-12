package command;

import model.Clock;
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

        }
    }

    @Override
    public boolean checkCondition(String command) {
        return simpleCheck(command, " follow ");
    }
}