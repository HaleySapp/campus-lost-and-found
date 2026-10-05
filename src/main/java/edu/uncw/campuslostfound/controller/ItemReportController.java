package edu.uncw.campuslostfound.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests related to lost and found item reports.
 *
 * Planned responsibilities:
 * - Receive lost item report submissions from the user interface
 * - Receive found item report submissions from the user interface
 * - Send report requests to ItemReportService
 * - Return submission results to the user interface
 *
 * Related critical features:
 * - Community user posts a lost item.
 * - Community user posts a found item.
 *
 * Prototype responsibilities:
 * - Lost item report: Tyler
 * - Found item report: Haley
 */

@RestController
@RequestMapping("/api/reports")
public class ItemReportController {

    private final edu.uncw.campuslostfound.service.ItemReportService service;

    public ItemReportController(
            edu.uncw.campuslostfound.service.ItemReportService service) {
        this.service = service;
    }

    @PostMapping("/lost")
    public String submitLostItem(
            @RequestBody edu.uncw.campuslostfound.model.LostItemReport report) {

        try {
            service.submitLostItem(report);
            return "Lost item report submitted for security review.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}