package ui;

import services.UserService;

import java.util.Scanner;

public class RegisterView {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService(); // You may pass this from outside

    public void show() {
        System.out.println("\n===== REGISTER =====");
        System.out.println("[1] Register as Renter");
        System.out.println("[2] Register as Lender");
        System.out.print("➤ Choose account type: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                registerRenter();
                break;
            case "2":
                registerLender();
                break;
            default:
                System.out.println("❌ Invalid choice. Please try again.");
        }
    }

    private void registerRenter() {
        System.out.println("\n--- RENTER REGISTRATION ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        userService.registerRenter(username, password, city);
        System.out.println("✅ Renter account created successfully!");
    }

    private void registerLender() {
        System.out.println("\n--- LENDER REGISTRATION ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        userService.registerLender(username, password, city);
        System.out.println("✅ Lender account created successfully!");
    }
}
