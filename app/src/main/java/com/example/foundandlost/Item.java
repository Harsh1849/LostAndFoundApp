package com.example.foundandlost;

public class Item {
    // Private fields for each attribute of an item
    private int id;  // Database ID, not required during creation but useful for database operations
    private String name;  // Owner's name or finder's name depending on the context
    private String phone;  // Contact phone number
    private String description;  // Description of the item
    private String date;  // Date when the item was lost or found
    private String location;  // Location of the item
    private String status;  // 'Lost' or 'Found'

    // Default constructor
    public Item() {
    }

    // Constructor with parameters for all fields except id
    public Item(String name, String phone, String description, String date, String location, String status) {
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.status = status;
    }

    // Getter and setter methods for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
