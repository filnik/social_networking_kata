package command;

import model.Clock;
import model.Message;
import model.User;
import model.UserFactory;
import service.Output;

public class ReadCommand extends Command {

    public ReadCommand(Output outputStream, UserFactory userFactory, Clock clock) {
        super(outputStream, userFactory, clock);
    }

    @Override
    public void execute(String command) {
        User user = userFactory.get(command);
        printMessages(user);
    }

    private void printMessages(User user) {
        for (Message message : user.getMessages()){
            outputStream.out(message.toString());
        }
    }

    @Override
    public boolean checkCondition(String command) {
        return userFactory.exists(command);
    }
}
