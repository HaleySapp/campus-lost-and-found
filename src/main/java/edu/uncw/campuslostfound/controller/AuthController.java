

package edu.uncw.campuslostfound.controller;
import jakarta.servlet.http.HttpSession;


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
    public Map<String, Object> login(@RequestBody Map<String, String> request, HttpSession session) {

        // Get what the user typed
        String campusId = request.get("campusId");
        String password = request.get("password");

        // Check the login information
        boolean success = authService.login(campusId, password);

        // Send the result back to the webpage
        if (success) {

    // Remember which user logged in
    session.setAttribute("loggedInCampusId", campusId);

    return Map.of("success", true, "message", "Login Successful!");

} else {

    // Clear any previous login
    session.invalidate();

    return Map.of("success", false, "message", "Login Failed.");
}
    }
    
    // Check if a user is currently logged in
    @GetMapping("/status")
    public Map<String, Object> status(
            @SessionAttribute(value = "loggedInCampusId", required = false) String campusId) {

        // Check if the session contains a logged in user
        boolean loggedIn = campusId != null;

        return Map.of("authenticated", loggedIn);
    }
    
    // Log the current user out
    @PostMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {

        // End the user's current session
        session.invalidate();

        return Map.of("success", true, "message", "Logged out successfully");
    }


}

