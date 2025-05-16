package models;
public class Lender extends user {
    public Lender(String username, String password, String city) {
        super(username, password, city);
    }

    @Override
    public void displayHomePage() {
        System.out.println("Welcome, Lender " + getUsername());
    }
}
