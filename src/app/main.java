package app;
import ui.MainMenu;

public class main {
    public static void main(String[] args) {
        // Show banner or welcome message
        System.out.println("=======================================");
        System.out.println("      WELCOME TO RENT & CONNECT        ");
        System.out.println("=======================================");

        // Start the main menu
        new MainMenu().show();
    }
}
