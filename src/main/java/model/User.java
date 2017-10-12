package model;

import java.util.ArrayList;

public class User {
    private final Clock clock;
    private ArrayList<Message> messages = new ArrayList<>();

    public User(Clock clock) {
        this.clock = clock;
    }

    public void addMessage(String message) {
        messages.add(0, new Message(message, clock.now()));
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
