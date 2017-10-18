package presenter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MessagePresenter {
    private static final String MINUTES = " (%s minutes ago)";
    private static final String MINUTE = " (%s minute ago)";
    private static final String SECONDS = " (%s seconds ago)";
    private static final String SECOND = " (%s second ago)";
    private final String username;
    private final String message;

    public MessagePresenter(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String showMessage(LocalDateTime now, LocalDateTime timestamp) {
        long minutes = timestamp.until(now, ChronoUnit.MINUTES);
        long seconds = timestamp.until(now, ChronoUnit.SECONDS);
        if (minutes > 0)
            return message + String.format(minutes == 1 ? MINUTE : MINUTES, minutes);
        else
            return message + String.format(seconds == 1 ? SECOND : SECONDS, seconds);
    }

    public String showWallMessage(LocalDateTime now, LocalDateTime timestamp) {
        return username + " - " + showMessage(now, timestamp);
    }
}
