package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Message {
    private final String message;
    private final LocalDateTime timestamp;

    public Message(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        LocalDateTime now = LocalDateTime.now();
        long minutes = now.until(timestamp, ChronoUnit.MINUTES);
        return message + String.format(" (%s minutes ago)", minutes);
    }
}
