package ui;

import models.HouseListing;
import models.Lender;
import services.HouseService;

import java.util.ArrayList;
import java.util.Scanner;

public class LenderDashboard {

    private final Lender lender;
    private final Scanner scanner = new Scanner(System.in);
    private final HouseService houseService = new HouseService(); // Should be passed in ideally

    public LenderDashboard(Lender lender) {
        this.lender = lender;
    }

    public void show() {
        while (true) {
            System.out.println("\n===== LENDER DASHBOARD =====");
            System.out.println("[1] Add a new house");
            System.out.println("[2] View all listings");
            System.out.println("[3] Update a listing");
            System.out.println("[4] Logout");

            System.out.print("➤ Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewHouse();
                    break;
                case "2":
                    viewAllListings();
                    break;
                case "3":
                    updateHouse();
                    break;
                case "4":
                    System.out.println("✅ Logged out.");
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void addNewHouse() {
        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("Type (apartment, condo, etc.): ");
        String type = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Number of bedrooms: ");
        int bedrooms = Integer.parseInt(scanner.nextLine());

        System.out.print("Available (true/false): ");
        boolean available = Boolean.parseBoolean(scanner.nextLine());

        HouseListing house = houseService.addHouse(location, type, price, bedrooms, available);
        System.out.println("✅ House added with ID: " + house.getListingId());
    }

    private void viewAllListings() {
        ArrayList<HouseListing> listings = houseService.getAllListings();

        if (listings.isEmpty()) {
            System.out.println("No listings yet.");
            return;
        }

        for (HouseListing house : listings) {
            displayHouse(house);
        }
    }

    private void updateHouse() {
        System.out.print("Enter the ID of the listing to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        HouseListing house = houseService.getById(id);
        if (house == null) {
            System.out.println("❌ No listing found with ID " + id);
            return;
        }

        System.out.println("Leave field blank to keep current value.");
        System.out.print("New Location (" + house.getLocation() + "): ");
        String location = scanner.nextLine();
        if (!location.isEmpty()) house.setLocation(location);

        System.out.print("New Type (" + house.getHouseType() + "): ");
        String type = scanner.nextLine();
        if (!type.isEmpty()) house.setHouseType(type);

        System.out.print("New Price (" + house.getPrice() + "): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) house.setPrice(Double.parseDouble(priceInput));

        System.out.print("New Bedrooms (" + house.getBedrooms() + "): ");
        String bedInput = scanner.nextLine();
        if (!bedInput.isEmpty()) house.setBedrooms(Integer.parseInt(bedInput));

        System.out.print("Is Available? (" + house.isAvailable() + "): ");
        String availInput = scanner.nextLine();
        if (!availInput.isEmpty()) house.setAvailable(Boolean.parseBoolean(availInput));

        System.out.println("✅ Listing updated!");
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
