package services;

import models.Lender;
import models.Renter;
import models.user;
import java.util.ArrayList;
import java.util.Optional;

public class UserService {
    private ArrayList<Renter> renters = new ArrayList<>();
    private ArrayList<Lender> lenders = new ArrayList<>();

    public boolean registerRenter(String username, String password, String city) {
        renters.add(new Renter(username, password, city));
        return true;
    }

    public boolean registerLender(String username, String password, String city) {
        lenders.add(new Lender(username, password, city));
        return true;
    }

    public Optional<user> login(String username, String password) {
        for (Renter r : renters) {
            if (r.getUsername().equals(username) && r.getPassword().equals(password)) {
                return Optional.of(r);
            }
        }

        for (Lender l : lenders) {
            if (l.getUsername().equals(username) && l.getPassword().equals(password)) {
                return Optional.of(l);
            }
        }

        return Optional.empty(); // Login failed
    }

    public ArrayList<Renter> getRenters() {
        return renters;
    }

    public ArrayList<Lender> getLenders() {
        return lenders;
    }
}
