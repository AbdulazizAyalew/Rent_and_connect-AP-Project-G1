package ui;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void show() {
        while (true) {
            System.out.println("\n===== RENT & CONNECT =====");
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[3] Exit");
            System.out.print("➤ Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    new LoginView().show();
                    break;
                case "2":
                    new RegisterView().show();
                    break;
                case "3":
                    System.out.println("✅ Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
}
