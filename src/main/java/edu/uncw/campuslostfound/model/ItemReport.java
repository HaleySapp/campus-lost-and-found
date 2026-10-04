package edu.uncw.campuslostfound.model;

/**
 * Base class for lost and found item reports.
 *
 * Common report information may include:
 * - Item name
 * - Category
 * - Date lost or found
 * - Location
 * - Color, brand, and distinguishing characteristics
 * - Photo
 * - Additional description
 * - Contact information
 * - Report status
 *
 * Submitted reports will be reviewed by security staff
 * before becoming publicly searchable.
 */
public class ItemReport {

    private String itemName;
    private String category;
    private String date;
    private String location;
    private String color;
    private String brand;
    private String description;
    private String photoPath;
    private String contactInfo;
    private ReportStatus status;

}