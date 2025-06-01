package models;

import java.math.BigDecimal;

public class HouseListing {
    private int listingId;
    private int lender_id;
    private String title;
    private String description;
    private String location;
    private String houseType;
    private BigDecimal price;
    private boolean available;

    // Constructor, Getters, Setters
    // ✅ Constructor
    public HouseListing(){

    }
    public HouseListing(String title, String description, String houseType,  String location,  BigDecimal price, boolean available) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.houseType = houseType;
        this.price = price;
        this.available = available;
    }

    // ✅ Getters
    public String getListingTitle(){
        return title;
    }
    
    public String getListingDescription(){
        return description;
    }
    
    public int getLenderId(){
        return lender_id;
    }

    public int getListingId() {
        return listingId;
    }

    public String getLocation() {
        return location;
    }

    public String getHouseType() {
        return houseType;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public boolean isAvailable() {
        return available;
    }
    

    // ✅ Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void setLenderId(int id){
        this.lender_id = id;
    }
}
