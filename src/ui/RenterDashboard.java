package ui;

import models.Renter;
import models.HouseListing;
import services.HouseService;

import java.util.ArrayList;
import java.util.Scanner;

public class RenterDashboard {

    private final Renter renter;
    private final Scanner scanner = new Scanner(System.in);
    private final HouseService houseService = new HouseService(); // Ideally passed from outside

    public RenterDashboard(Renter renter) {
        this.renter = renter;
    }

    public void show() {
        while (true) {
            System.out.println("\n===== RENTER DASHBOARD =====");
            System.out.println("[1] View all listings");
            System.out.println("[2] Search by city and max price");
            System.out.println("[3] Logout");

            System.out.print("➤ Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllListings();
                    break;
                case "2":
                    searchListings();
                    break;
                case "3":
                    System.out.println("✅ Logged out.");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllListings() {
        ArrayList<HouseListing> listings = houseService.getAllListings();
        System.out.println("\n--- Available House Listings ---");

        if (listings.isEmpty()) {
            System.out.println("No listings available.");
            return;
        }

        for (HouseListing house : listings) {
            displayHouse(house);
        }
    }

    private void searchListings() {
        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        System.out.print("Enter max price: ");
        double maxPrice = Double.parseDouble(scanner.nextLine());

        ArrayList<HouseListing> results = houseService.searchByCityAndMaxPrice(city, maxPrice);

        if (results.isEmpty()) {
            System.out.println("No listings found.");
        } else {
            for (HouseListing house : results) {
                displayHouse(house);
            }
        }
    }

    private void displayHouse(HouseListing house) {
        System.out.println("ID: " + house.getListingId());
        System.out.println("Location: " + house.getLocation());
        System.out.println("Type: " + house.getHouseType());
        System.out.println("Price: " + house.getPrice());
        System.out.println("Bedrooms: " + house.getBedrooms());
        System.out.println("Availability: " + (house.isAvailable() ? "Available" : "Not Available"));
        System.out.println("------------------------");
    }
}
