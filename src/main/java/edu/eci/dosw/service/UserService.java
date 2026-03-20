package edu.eci.dosw.service;

import edu.eci.dosw.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public User getUserById(String id) {
        return users.get(id);
    }
}