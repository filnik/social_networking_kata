package command;

import model.Clock;
import model.UserFactory;
import service.Output;

public abstract class Command {
    protected final Output outputStream;
    protected final UserFactory userFactory;
    protected final Clock clock;

    public Command(Output outputStream, UserFactory userFactory, Clock clock) {
        this.outputStream = outputStream;
        this.userFactory = userFactory;
        this.clock = clock;
    }

    public abstract void execute(String command);
    abstract boolean checkCondition(String command);

    protected boolean simpleCheck(String command, String splitter) {
        String[] split = command.split(splitter);
        return userFactory.exists(split[0]);
    }
}
