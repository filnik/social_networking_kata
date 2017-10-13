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
    public abstract boolean checkCondition(String command);
}