package ui;

import models.Renter;
import models.Lender;
import services.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginView  {
    
    public LoginView() throws SQLException{

    }

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService(); 

    public void show() {
        System.out.println("\n===== LOGIN =====");
        System.out.println("[1] Login as Renter");
        System.out.println("[2] Login as Lender");
        System.out.print("➤ Choose account type: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                login_Renter();
                break;
            case "2":
                login_lender();
                break;
            default:
                System.out.println("❌ Invalid choice. Please try again.");
        }

    }
        private void login_Renter() {

            System.out.println("\n===== LOGIN =====");
            System.out.print("➤ Username: ");
            String username = scanner.nextLine();

            System.out.print("➤ Password: ");
            String password = scanner.nextLine();
            try {
                Renter renter = userService.loginRenter(username, password);
                if (renter != null) {
                    System.out.println("✅ Login successful!");
                    RenterDashboard dashboard = new RenterDashboard(renter);
                    dashboard.showMenu();
                }
                else {
                System.out.println("❌ Invalid credentials. Please try again.");
                }
            } catch (SQLException e) {
                
            }
        }

        private void login_lender() {

            System.out.println("\n===== LOGIN =====");
            System.out.print("➤ Username: ");
            String l_username = scanner.nextLine();

            System.out.print("➤ Password: ");
            String l_password = scanner.nextLine();
            try {
                Lender lender = userService.loginLender(l_username, l_password);
                if (lender != null) {
                    System.out.println("✅ Login successful!");
                    LenderDashboard dashboard = new LenderDashboard(lender);
                    dashboard.showMenu();
                }
                else {
                System.out.println("❌ Invalid credentials. Please try again.");
                }
            } catch (SQLException e) {
                
            }
        }

}