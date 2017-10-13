package command;

import model.Clock;
import model.Message;
import model.User;
import model.UserFactory;
import service.Output;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WallCommand extends Command {

    public WallCommand(Output outputStream, UserFactory userFactory, Clock clock) {
        super(outputStream, userFactory, clock);
    }

    @Override
    public void execute(String command) {
        User user = userFactory.get(command.replace(" wall", ""));
        List<Message> messages = getAllMessages(user);

        for (Message message : messages){
            outputStream.out(message.toWallString());
        }
    }

    private List<Message> getAllMessages(User user) {
        List<Message> messages = user.getMessages();
        for (User singleUser: user.getFollowees()){
            messages.addAll(singleUser.getMessages());
        }
        Collections.sort(messages, Comparator.comparing(Message::getTimestamp));
        return messages;
    }

    @Override
    public boolean checkCondition(String command) {
        boolean result = command.endsWith("wall");
        return result && userFactory.exists(command.replace(" wall", ""));
    }
}
