package command;

import model.Clock;
import model.Message;
import model.User;
import model.UserRepository;
import service.Output;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WallCommand extends Command {

    public WallCommand(Output outputStream, UserRepository userRepository, Clock clock) {
        super(outputStream, userRepository, clock);
    }

    @Override
    public void execute(String command) {
        User user = userRepository.load(command.replace(" wall", ""));
        List<Message> messages = getAllMessages(user);
        printMessages(messages);
    }

    private void printMessages(List<Message> messages) {
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
        return result && userRepository.exists(command.replace(" wall", ""));
    }
}
