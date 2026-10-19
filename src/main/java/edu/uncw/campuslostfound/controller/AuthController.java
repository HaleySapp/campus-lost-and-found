
package edu.uncw.campuslostfound.controller;

import edu.uncw.campuslostfound.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Handles checking user logins
    private final AuthenticationService authService =
            new AuthenticationService();

    // Receives login requests from the website
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {

        // Get what the user typed
        String campusId = request.get("campusId");
        String password = request.get("password");

        // Check the login information
        boolean success = authService.login(campusId, password);

        // Send the result back to the webpage
        if (success) {
            return Map.of("success", true, "message", "Login Successful!");
        } else {
            return Map.of("success", false, "message", "Login Failed.");
        }
    }
}
