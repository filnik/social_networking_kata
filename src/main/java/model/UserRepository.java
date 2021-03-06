package model;

import java.util.HashMap;

public class UserRepository {
    private final HashMap<String, User> users = new HashMap<String, User>();

    public boolean exists(String... usersToParse) {
        for (String user : usersToParse){
            if (users.containsKey(user.toLowerCase()))
                return true;
        }
        return false;
    }

    public User load(String user) {
        return users.get(user.toLowerCase());
    }

    public void save(User user) {
        users.put(user.getUsername().toLowerCase(), user);
    }
}
