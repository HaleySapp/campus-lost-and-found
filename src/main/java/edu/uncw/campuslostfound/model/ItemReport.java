package edu.uncw.campuslostfound.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ItemReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String category;
    private String date;
    private String location;
    private String color;
    private String brand;
    private String description;
    private String photoPath;
    private String contactInfo;

    @Enumerated(EnumType.STRING)
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
    public Long getId() {return id;}

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

}