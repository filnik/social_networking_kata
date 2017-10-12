package command;

import model.Clock;
import model.Message;
import model.User;
import model.UserFactory;
import service.Output;

public class WallCommand extends Command {

    public WallCommand(Output outputStream, UserFactory userFactory, Clock clock) {
        super(outputStream, userFactory, clock);
    }

    @Override
    public void execute(String command) {
        User user = userFactory.get(command.toLowerCase());
        for (Message message : user.getMessages()){
            outputStream.out(message.toString());
        }
    }

    @Override
    public boolean checkCondition(String command) {
        return simpleCheck(command, "wall");
    }
}
