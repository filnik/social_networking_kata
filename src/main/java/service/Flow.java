package service;

public interface Flow {
    void post(String user, String message);
    void start();
}
