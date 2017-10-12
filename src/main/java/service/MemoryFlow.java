package service;

import model.Clock;
import model.Message;
import model.User;
import model.UserFactory;

import java.util.HashMap;

public class MemoryFlow implements Flow {
    private final Clock clock;
    private final Input inputStream;
    private final Output outputStream;
    private final UserFactory userFactory;

    public MemoryFlow(Clock clock, Input inputStream, Output outputStream) {
        this.clock = clock;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.userFactory = new UserFactory();
    }

    public void start() {
        while(inputStream.hasNext()){
            if (inputStream.hasNext())
                executeCommand(inputStream.next());
        }
    }

    private void executeCommand(String next) {
        if (next.contains(" -> ")) {
            String[] split = next.split(" -> ");
            final String username = split[0];
            final String message = split[1];
            User user;
            if (userFactory.exists(username)) {
                user = userFactory.get(username.toLowerCase());
            } else {
                user = new User(username.toLowerCase(), clock);
                userFactory.put(user);
            }
            user.addMessage(message);
        } else if (next.contains("follows")){
            String[] users = next.split(" follows ");
            if (userFactory.exists(users)){

            }
        } else if (next.endsWith("wall")){
            User user = userFactory.get(next.toLowerCase());
            for (Message message : user.getMessages()){
                outputStream.out(message.toString());
            }
        } else if (userFactory.exists(next)){
            User user = userFactory.get(next.toLowerCase());
            for (Message message : user.getMessages()){
                outputStream.out(message.toString());
            }
        }
    }
}
