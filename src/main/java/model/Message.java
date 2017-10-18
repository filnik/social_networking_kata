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
        this.messagePresenter = new MessagePresenter(message);
    }

    @Override
    public String toString() {
        LocalDateTime now = clock.now();
        long minutes = timestamp.until(now, ChronoUnit.MINUTES);
        long seconds = timestamp.until(now, ChronoUnit.SECONDS);
        return messagePresenter.showMessage(minutes, seconds);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toWallString() {
        return user.getUsername() + " - " + toString();
    }
}
