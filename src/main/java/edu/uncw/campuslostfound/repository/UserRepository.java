package edu.uncw.campuslostfound.repository;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    // Stores our test users
    private final Map<String, String> users = new HashMap<>();

    // Adds a test account
    public UserRepository() {
        users.put("sam123", "password123");
    }

    // Finds the password for a Campus ID
    public String findPasswordByCampusId(String campusId) {
        return users.get(campusId);
    }
}
