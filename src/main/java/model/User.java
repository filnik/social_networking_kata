package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final Clock clock;
    private List<Message> messages = new ArrayList<>();
    private List<User> followees = new ArrayList<>();

    public User(String username, Clock clock) {
        this.username = username;
        this.clock = clock;
    }

    public void addMessage(String message) {
        messages.add(0, new Message(this, message, clock));
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getUsername() {
        return username;
    }

    public void follows(User followee) {
        followees.add(followee);
    }

    public List<User> getFollowees(){
        return followees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
