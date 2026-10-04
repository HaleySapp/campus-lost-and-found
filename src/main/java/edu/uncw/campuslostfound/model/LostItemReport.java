package edu.uncw.campuslostfound.model;

/**
 * Represents a lost item report submitted to the system.
 *
 * A lost item report inherits the common information
 * defined by ItemReport.
 *
 * Related critical feature:
 * Community user posts a lost item.
 *
 * Prototype responsibility: Tyler
 */
public class LostItemReport extends ItemReport {

    public LostItemReport(String itemName,
                          String category,
                          String date,
                          String location,
                          String color,
                          String brand,
                          String description,
                          String photoPath,
                          String contactInfo) {

        super(itemName,
                category,
                date,
                location,
                color,
                brand,
                description,
                photoPath,
                contactInfo);
    }
}