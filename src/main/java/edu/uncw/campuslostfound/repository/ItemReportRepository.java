package edu.uncw.campuslostfound.repository;

import edu.uncw.campuslostfound.model.ItemReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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

@Repository
public interface ItemReportRepository extends JpaRepository<ItemReport, Long> {

    default List<ItemReport> getAllReports() {
        return findAll();
    }
}