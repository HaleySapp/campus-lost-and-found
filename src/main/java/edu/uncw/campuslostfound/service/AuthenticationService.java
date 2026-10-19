
package edu.uncw.campuslostfound.service;

import edu.uncw.campuslostfound.repository.UserRepository;

public class AuthenticationService {

    // Access our test users
    private final UserRepository userRepository = new UserRepository();

    // Checks the Campus ID and password
    public boolean login(String campusId, String password) {

        // Find the password for this user
        String storedPassword = userRepository.findPasswordByCampusId(campusId);

        // Check if the passwords match
        return storedPassword != null && storedPassword.equals(password);
    }
}
