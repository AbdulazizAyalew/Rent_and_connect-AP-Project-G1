public class Renter extends user {
    public Renter(String username, String password, String city) {
        super(username, password, city);
    }

    @Override
    public void displayHomePage() {
        System.out.println("Welcome, Renter " + getUsername());
    }
}
