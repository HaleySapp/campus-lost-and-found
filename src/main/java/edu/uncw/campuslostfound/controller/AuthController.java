

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
    
    // Remember the user's role
    session.setAttribute("loggedInRole", authService.getRole(campusId));


    return Map.of("success", true, "message", "Login Successful!");

} else {

    // Clear any previous login
    session.invalidate();

    return Map.of("success", false, "message", "Login Failed.");
}
    }
    
    
    // Check if a user is logged in and get their role
    @GetMapping("/status")
    public Map<String, Object> status(
            @SessionAttribute(value = "loggedInCampusId", required = false) String campusId,
            @SessionAttribute(value = "loggedInRole", required = false) String role) {

        // Check if anyone is logged in
        if (campusId == null) {
            return Map.of("authenticated", false);
        }

        // Return the user's login status and role
        return Map.of(
                "authenticated", true,
                "role", role == null ? "UNASSIGNED" : role
        );
    }

    
    // Log the current user out
    @PostMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {

        // End the user's current session
        session.invalidate();

        return Map.of("success", true, "message", "Logged out successfully");
    }

    
    // Check if the user has Security Staff permission
    @GetMapping("/security-check")
    public Map<String, Object> securityCheck(
            @SessionAttribute(value = "loggedInRole", required = false) String role,
            jakarta.servlet.http.HttpServletResponse response) {

        // Only Security Staff can access this endpoint
        if (!"SECURITY_STAFF".equals(role)) {
            response.setStatus(403);
            return Map.of("access", "denied");
        }

        return Map.of("access", "granted");
    }
    
    // Check if the user has Administrator permission
    @GetMapping("/admin-check")
    public Map<String, Object> adminCheck(
            @SessionAttribute(value = "loggedInRole", required = false) String role,
            jakarta.servlet.http.HttpServletResponse response) {

        // Only Campus Administrators can access this
        if (!"CAMPUS_ADMINISTRATOR".equals(role)) {
            response.setStatus(403);
            return Map.of("access", "denied");
        }

        // Administrator has permission
        return Map.of("access", "granted");
    }




}
