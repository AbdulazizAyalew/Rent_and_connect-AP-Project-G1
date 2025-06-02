package models;
public abstract class user {
    protected String username;
    protected String password;
    protected String city;

    public user(String username, String city, String password) {
        this.username = username;
        this.password = password;
        this.city = city;
    }


    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getCity() { return city; }
}
