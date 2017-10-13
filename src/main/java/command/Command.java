package command;

import model.Clock;
import model.UserRepository;
import service.Output;

public abstract class Command {
    protected final Output outputStream;
    protected final UserRepository userRepository;
    protected final Clock clock;

    public Command(Output outputStream, UserRepository userRepository, Clock clock) {
        this.outputStream = outputStream;
        this.userRepository = userRepository;
        this.clock = clock;
    }

    public abstract void execute(String command);
    public abstract boolean checkCondition(String command);
}