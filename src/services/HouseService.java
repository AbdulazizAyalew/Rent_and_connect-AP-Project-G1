package services;

import models.*;
import java.util.ArrayList;

public class HouseService {
    private ArrayList<HouseListing> listings = new ArrayList<>();
    private int nextListingId = 1;

    public HouseListing addHouse(String location, String type, double price, int bedrooms, boolean available) {
        HouseListing house = new HouseListing(nextListingId++, location, type, price, bedrooms, available);
        listings.add(house);
        return house;
    }

    public ArrayList<HouseListing> searchByCityAndMaxPrice(String city, double maxPrice) {
        ArrayList<HouseListing> results = new ArrayList<>();
        for (HouseListing h : listings) {
            if (h.getLocation().equalsIgnoreCase(city) && h.getPrice() <= maxPrice && h.isAvailable()) {
                results.add(h);
            }
        }
        return results;
    }

    public ArrayList<HouseListing> getAllListings() {
        return listings;
    }

    public HouseListing getById(int id) {
        for (HouseListing h : listings) {
            if (h.getListingId() == id) {
                return h;
            }
        }
        return null;
    }
}
