package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Message {
    private final String message;
    private final LocalDateTime timestamp;

    public Message(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        LocalDateTime now = LocalDateTime.now();
        long minutes = timestamp.until(now, ChronoUnit.MINUTES);
        return message + String.format(" (%s minutes ago)", minutes);
    }
}
