package models;
public class HouseListing {
    private int listingId;
    private String location;
    private String houseType;
    private double price;
    private int bedrooms;
    private boolean available;

    // Constructor, Getters, Setters
    // ✅ Constructor
    public HouseListing(int listingId, String location, String houseType, double price, int bedrooms, boolean available) {
        this.listingId = listingId;
        this.location = location;
        this.houseType = houseType;
        this.price = price;
        this.bedrooms = bedrooms;
        this.available = available;
    }

    // ✅ Getters
    public int getListingId() {
        return listingId;
    }

    public String getLocation() {
        return location;
    }

    public String getHouseType() {
        return houseType;
    }

    public double getPrice() {
        return price;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public boolean isAvailable() {
        return available;
    }

    // ✅ Setters
    public void setLocation(String location) {
        this.location = location;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
