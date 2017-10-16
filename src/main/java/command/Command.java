package command;

import model.Clock;
import model.UserRepository;
import service.Output;

public abstract class Command {
    protected final Output output;
    protected final UserRepository userRepository;
    protected final Clock clock;

    public Command(Output output, UserRepository userRepository, Clock clock) {
        this.output = output;
        this.userRepository = userRepository;
        this.clock = clock;
    }

    public abstract void execute(String command);
    public abstract boolean checkCondition(String command);
}