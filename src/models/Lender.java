package models;
public class Lender extends user {
    public Lender(String username, String city, String password) {
        super(username, city, password);
    }

    @Override
    public void displayHomePage() {
        System.out.println("Welcome, Lender " + getUsername());
    }
}
