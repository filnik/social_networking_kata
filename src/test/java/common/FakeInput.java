package common;

import service.Input;

import java.util.ArrayList;

public class FakeInput implements Input {

    public ArrayList<String> strings = new ArrayList<>();

    public void post(String message){
        strings.add(message);
    }

    @Override
    public boolean hasNextLine() {
        return !strings.isEmpty();
    }

    @Override
    public String nextLine() {
        String result = strings.get(0);
        strings.remove(0);
        return result;
    }
}