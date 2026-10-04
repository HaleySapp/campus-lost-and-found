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

    public ItemReport() {
        this.status = ReportStatus.PENDING_REVIEW;
    }

    public ItemReport(String itemName,
                      String category,
                      String date,
                      String location,
                      String color,
                      String brand,
                      String description,
                      String photoPath,
                      String contactInfo) {

        this.itemName = itemName;
        this.category = category;
        this.date = date;
        this.location = location;
        this.color = color;
        this.brand = brand;
        this.description = description;
        this.photoPath = photoPath;
        this.contactInfo = contactInfo;
        this.status = ReportStatus.PENDING_REVIEW;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public ReportStatus getStatus() {
        return status;
    }

}