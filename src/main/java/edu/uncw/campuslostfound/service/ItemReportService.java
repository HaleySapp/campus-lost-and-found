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

        java.util.List<String> invalidFields = new java.util.ArrayList<>();

        validateRequiredField(report.getItemName(), "Item Name", invalidFields);
        validateRequiredField(report.getCategory(), "Category", invalidFields);
        validateRequiredField(report.getDate(), "Date Lost", invalidFields);
        validateRequiredField(report.getLocation(), "Location", invalidFields);
        validateRequiredField(report.getColor(), "Color", invalidFields);
        validateRequiredField(report.getBrand(), "Brand", invalidFields);
        validateRequiredField(report.getDescription(), "Description", invalidFields);

        validateSafeField(report.getContactInfo(), "Contact Information", invalidFields);
        validateSafeField(report.getPhotoPath(), "Photo", invalidFields);

        if (!invalidFields.isEmpty()) {
            throw new IllegalArgumentException(
                    "Submission failed. Please review: "
                            + String.join(", ", invalidFields));
        }

        report.setItemName(report.getItemName().trim());
        report.setCategory(report.getCategory().trim());
        report.setDate(report.getDate().trim());
        report.setLocation(report.getLocation().trim());
        report.setColor(report.getColor().trim());
        report.setBrand(report.getBrand().trim());
        report.setDescription(report.getDescription().trim());

        if (report.getContactInfo() != null) {
            report.setContactInfo(report.getContactInfo().trim());
        }

        if (report.getPhotoPath() != null) {
            report.setPhotoPath(report.getPhotoPath().trim());
        }

        repository.save(report);
    }

    public java.util.List<edu.uncw.campuslostfound.model.ItemReport> getAllReports() {
        return repository.getAllReports();
    }

    private void validateRequiredField(
            String value,
            String fieldName,
            java.util.List<String> invalidFields) {

        if (isBlank(value)) {
            invalidFields.add(fieldName + " is required");
            return;
        }

        validateSafeField(value, fieldName, invalidFields);
    }

    private void validateSafeField(
            String value,
            String fieldName,
            java.util.List<String> invalidFields) {

        if (value == null || value.isBlank()) {
            return;
        }

        String lowerValue = value.toLowerCase();

        if (value.contains("<")
                || value.contains(">")
                || lowerValue.contains("javascript:")
                || lowerValue.contains("<script")) {

            invalidFields.add(fieldName + " contains unsafe input");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}