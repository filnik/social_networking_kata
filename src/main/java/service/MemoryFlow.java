package service;

public class MemoryFlow implements Flow {
    private final Input inputStream;
    private final Output outputStream;

    public MemoryFlow(Input inputStream, Output outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void post(String user, String message) {

    }

    @Override
    public void start() {

    }
}
