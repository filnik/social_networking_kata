package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Message {
    private final String message;
    private final LocalDateTime timestamp;
    private final Clock clock;

    public Message(String message, Clock clock) {
        this.message = message;
        this.timestamp = clock.now();
        this.clock = clock;
    }

    @Override
    public String toString() {
        LocalDateTime now = clock.now();
        long minutes = now.until(timestamp, ChronoUnit.MINUTES);
        return message + String.format(" (%s minutes ago)", minutes);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
