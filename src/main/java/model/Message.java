package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Message {
    public static final String MINUTES = " (%s minutes ago)";
    public static final String MINUTE = " (%s minute ago)";
    public static final String SECONDS = " (%s seconds ago)";
    public static final String SECOND = " (%s second ago)";
    private final User user;
    private final String message;
    private final LocalDateTime timestamp;
    private final Clock clock;

    public Message(User user, String message, Clock clock) {
        this.user = user;
        this.message = message;
        this.timestamp = clock.now();
        this.clock = clock;
    }

    @Override
    public String toString() {
        LocalDateTime now = clock.now();
        long minutes = timestamp.until(now, ChronoUnit.MINUTES);
        long seconds = timestamp.until(now, ChronoUnit.SECONDS);
        if (minutes > 0)
            return message + String.format(minutes == 1 ? MINUTE : MINUTES, minutes);
        else
            return message + String.format(seconds == 1 ? SECONDS : SECONDS, seconds);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toWallString() {
        return user.getUsername() + " - " + toString();
    }
}
