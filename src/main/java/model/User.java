package model;

import java.util.ArrayList;

public class User {
    private ArrayList<Message> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(0, new Message(message));
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
