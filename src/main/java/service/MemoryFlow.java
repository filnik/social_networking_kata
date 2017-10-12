package service;

import model.Clock;
import model.Message;
import model.User;

import java.util.HashMap;

public class MemoryFlow implements Flow {
    private final Clock clock;
    private final Input inputStream;
    private final Output outputStream;
    private final HashMap<String, User> users = new HashMap<String, User>();

    public MemoryFlow(Clock clock, Input inputStream, Output outputStream) {
        this.clock = clock;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void start() {
        while(inputStream.hasNext()){
            if (inputStream.hasNext())
                executeCommand(inputStream.next());
        }
    }

    private void executeCommand(String next) {
        if (next.contains(" -> ")){
            String[] split = next.split(" -> ");
            final String username = split[0];
            final String message = split[1];
            User user;
            if (existsUser(username)){
                user = users.get(username.toLowerCase());
            } else {
                user = new User(clock);
                users.put(username.toLowerCase(), user);
            }
            user.addMessage(message);
        } else if (existsUser(next)){
            User user = users.get(next.toLowerCase());
            for (Message message : user.getMessages()){
                outputStream.out(message.toString());
            }
        }
    }

    private boolean existsUser(String user) {
        return users.containsKey(user.toLowerCase());
    }
}
