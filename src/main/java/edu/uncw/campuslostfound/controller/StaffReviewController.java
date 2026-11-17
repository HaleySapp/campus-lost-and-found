
package edu.uncw.campuslostfound.controller;

import edu.uncw.campuslostfound.model.ItemReport;
import edu.uncw.campuslostfound.model.ReportStatus;
import edu.uncw.campuslostfound.repository.ItemReportRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffReviewController {

    private final ItemReportRepository repository;

    public StaffReviewController(ItemReportRepository repository) {
        this.repository = repository;
    }

    // Security Staff can approve a pending report
    @PostMapping("/reports/{id}/approve")
    public ResponseEntity<Map<String, Object>> approveReport(
            @PathVariable Long id,
            HttpSession session) {

        // Check if anyone is logged in
        if (session.getAttribute("loggedInCampusId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Login required"));
        }

        // Only Security Staff can approve reports
        String role = (String) session.getAttribute("loggedInRole");

        if (!"SECURITY_STAFF".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Security Staff only"));
        }

        // Find the report in MySQL
        Optional<ItemReport> result = repository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Report not found"));
        }

        ItemReport report = result.get();

        // Only approve reports waiting for review
        if (report.getStatus() != ReportStatus.PENDING_REVIEW) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Report is not pending review"));
        }

        // Change status and save it
        report.setStatus(ReportStatus.APPROVED);
        repository.save(report);

        return ResponseEntity.ok(
                Map.of("message", "Report approved successfully")
        );
    }

    // Get reports waiting for Security Staff approval
    @GetMapping("/reports/pending")
    public ResponseEntity<?> getPendingReports(HttpSession session) {

        // Check if the user is logged in
        if (session.getAttribute("loggedInCampusId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Login required"));
        }

        // Check if the user is Security Staff
        String role = (String) session.getAttribute("loggedInRole");

        if (!"SECURITY_STAFF".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Security Staff only"));
        }

        // Get all reports from MySQL
        java.util.List<ItemReport> reports = repository.findAll();

        // Keep only reports that are waiting for approval
        java.util.List<ItemReport> pendingReports =
                new java.util.ArrayList<>();

        for (ItemReport report : reports) {
            if (report.getStatus() == ReportStatus.PENDING_REVIEW) {
                pendingReports.add(report);
            }
        }

        // Send pending reports to the webpage
        return ResponseEntity.ok(pendingReports);
    }

}
