package models;
public class Renter extends user {
    public Renter(String username, String city,String password) {
        super(username, city, password);
    }

    @Override
    public void displayHomePage() {
        System.out.println("Welcome, Renter " + getUsername());
    }
}
