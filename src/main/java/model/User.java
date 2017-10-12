package model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private final String username;
    private final Clock clock;
    private ArrayList<Message> messages = new ArrayList<>();

    public User(String username, Clock clock) {
        this.username = username;
        this.clock = clock;
    }

    public void addMessage(String message) {
        messages.add(0, new Message(message, clock));
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public String getUsername() {
        return username;
    }
}
