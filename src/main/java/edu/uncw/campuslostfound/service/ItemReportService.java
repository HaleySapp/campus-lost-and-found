package edu.uncw.campuslostfound.service;
import org.springframework.stereotype.Service;

/**
 * Contains the business logic for lost and found item reports.
 *
 * Planned responsibilities:
 * - Process lost item report submissions
 * - Process found item report submissions
 * - Validate required report information
 * - Set newly submitted reports to pending review
 * - Send report data to ItemReportRepository for storage
 *
 * Related critical features:
 * - Community user posts a lost item.
 * - Community user posts a found item.
 *
 * Prototype responsibilities:
 * - Lost item report: Tyler
 * - Found item report: Haley
 */
@Service
public class ItemReportService {

    private final edu.uncw.campuslostfound.repository.ItemReportRepository repository;

    public ItemReportService(
            edu.uncw.campuslostfound.repository.ItemReportRepository repository) {
        this.repository = repository;
    }

    public void submitLostItem(
            edu.uncw.campuslostfound.model.LostItemReport report) {

        if (report == null) {
            throw new IllegalArgumentException("Lost item report is required.");
        }

        if (isBlank(report.getItemName())) {
            throw new IllegalArgumentException("Item name is required.");
        }

        if (isBlank(report.getCategory())) {
            throw new IllegalArgumentException("Category is required.");
        }

        if (isBlank(report.getDate())) {
            throw new IllegalArgumentException("Date lost is required.");
        }

        if (isBlank(report.getLocation())) {
            throw new IllegalArgumentException("Location is required.");
        }

        if (isBlank(report.getColor())) {
            throw new IllegalArgumentException("Color is required.");
        }

        if (isBlank(report.getBrand())) {
            throw new IllegalArgumentException("Brand is required.");
        }

        if (isBlank(report.getDescription())) {
            throw new IllegalArgumentException(
                    "Description or distinguishing characteristics are required.");
        }

        repository.save(report);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}