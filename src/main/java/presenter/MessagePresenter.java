package presenter;

public class MessagePresenter {
    private static final String MINUTES = " (%s minutes ago)";
    private static final String MINUTE = " (%s minute ago)";
    private static final String SECONDS = " (%s seconds ago)";
    private static final String SECOND = " (%s second ago)";
    private final String message;

    public MessagePresenter(String message) {
        this.message = message;
    }

    public String showMessage(long minutes, long seconds) {
        if (minutes > 0)
            return message + String.format(minutes == 1 ? MINUTE : MINUTES, minutes);
        else
            return message + String.format(seconds == 1 ? SECOND : SECONDS, seconds);
    }
}
