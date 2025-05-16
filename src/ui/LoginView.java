package ui;

import models.Renter;
import models.Lender;
import models.user;
import services.UserService;

import java.util.Optional;
import java.util.Scanner;

public class LoginView {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService(); // You may pass this from MainMenu instead

    public void show() {
        System.out.println("\n===== LOGIN =====");
        System.out.print("➤ Username: ");
        String username = scanner.nextLine();

        System.out.print("➤ Password: ");
        String password = scanner.nextLine();

        Optional<user> userOpt = userService.login(username, password);

        if (userOpt.isPresent()) {
            user user = userOpt.get();
            System.out.println("✅ Login successful!");

            if (user instanceof Renter) {
                new RenterDashboard((Renter) user).show();
            } else if (user instanceof Lender) {
                new LenderDashboard((Lender) user).show();
            }
        } else {
            System.out.println("❌ Invalid credentials. Please try again.");
        }
    }
}
