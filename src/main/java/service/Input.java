package service;

import java.util.Iterator;

public interface Input extends Iterator<String> {
    void post(String input);
}
