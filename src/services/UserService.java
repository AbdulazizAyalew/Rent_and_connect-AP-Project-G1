package services;

import dao.UserDAO;
import models.Lender;
import models.Renter;

import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO;

    public UserService() throws SQLException {
        userDAO = new UserDAO();
    }

    public Boolean registerRenter(Renter renter) throws SQLException {
        return userDAO.insertRenter(renter);
    }

    public Boolean registerLender(Lender lender) throws SQLException {
        return userDAO.insertLender(lender);
    }

    public Renter loginRenter(String username,String password) throws SQLException {
        return userDAO.findRenterByUsername(username,password);
    }

    public Lender loginLender(String username, String password) throws SQLException {
        return userDAO.findLenderByUsername(username,password);
    }

}
