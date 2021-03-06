package command;

import model.Clock;
import model.Message;
import model.User;
import model.UserRepository;
import service.Output;

public class ReadCommand extends Command {

    public ReadCommand(Output output, UserRepository userRepository, Clock clock) {
        super(output, userRepository, clock);
    }

    @Override
    public void execute(String command) {
        User user = userRepository.load(command);
        printMessages(user);
    }

    private void printMessages(User user) {
        for (Message message : user.getMessages()){
            output.out(message.toString());
        }
    }

    @Override
    public boolean checkCondition(String command) {
        return userRepository.exists(command);
    }
}
