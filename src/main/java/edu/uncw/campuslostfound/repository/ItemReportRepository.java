package edu.uncw.campuslostfound.repository;

/**
 * Handles data access for lost and found item reports.
 *
 * Planned responsibilities:
 * - Store submitted lost item reports
 * - Store submitted found item reports
 * - Retrieve item reports
 * - Support updates to report information and status
 *
 * Related critical features:
 * - Community user posts a lost item.
 * - Community user posts a found item.
 *
 * Prototype responsibilities:
 * - Lost item report: Tyler
 * - Found item report: Haley
 */
public class ItemReportRepository {

    private final java.util.List<edu.uncw.campuslostfound.model.ItemReport> reports
            = new java.util.ArrayList<>();

    public void save(edu.uncw.campuslostfound.model.ItemReport report) {
        reports.add(report);
    }

    public java.util.List<edu.uncw.campuslostfound.model.ItemReport> getAllReports() {
        return reports;
    }
}