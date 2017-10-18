package model;

import presenter.MessagePresenter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Message {
    private final User user;
    private final LocalDateTime timestamp;
    private final Clock clock;
    private final MessagePresenter messagePresenter;

    public Message(User user, String message, Clock clock) {
        this.user = user;
        this.timestamp = clock.now();
        this.clock = clock;
        this.messagePresenter = new MessagePresenter(user.getUsername(), message);
    }

    @Override
    public String toString() {
        return messagePresenter.showMessage(clock.now(), timestamp);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toWallString() {
        return messagePresenter.showWallMessage(clock.now(), timestamp);
    }
}
