package edu.uncw.campuslostfound.repository;

/**
 * Handles data access for system users.
 *
 * Planned responsibilities:
 * - Retrieve user account information
 * - Support authentication requests
 * - Store and retrieve user role information
 *
 * Prototype use:
 * The initial prototype will use mock user account data
 * rather than connecting to the university's real authentication system.
 *
 * Related critical feature:
 * All users can log in.
 *
 * Prototype responsibility: Sam
 */


import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<String, String> users = new HashMap<>();
    // Store the role assigned to each user
    private final Map<String, String> roles = new HashMap<>();

    
public UserRepository() {

    // Community user account
    users.put("sam123", "password123");

    // Security staff account
    users.put("security123", "securitypass123");

    // Campus administrator account
    users.put("admin123", "adminpass123");
    
    // Assign a role to each user
    roles.put("sam123", "COMMUNITY_USER");
    roles.put("security123", "SECURITY_STAFF");
    roles.put("admin123", "CAMPUS_ADMINISTRATOR");

}


    public String findPasswordByCampusId(String campusId) {
        return users.get(campusId);
    }
    
    // Get the role assigned to a user
    public String findRoleByCampusId(String campusId) {
        return roles.get(campusId);
    }

}

