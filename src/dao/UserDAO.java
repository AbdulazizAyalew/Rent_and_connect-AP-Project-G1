package dao;

import db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Lender;
import models.Renter;
import models.user;

public class UserDAO {
    private Connection conn;

    public UserDAO() throws SQLException {
        conn = DatabaseConnection.getConnection();
    }

    // Insert a new renter
    public Boolean insertRenter(Renter renter) throws SQLException {
        String sql = "INSERT INTO renters (username, city, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, renter.getUsername());
            stmt.setString(2, renter.getCity());
            stmt.setString(3, renter.getPassword());

            int rowsaffected = stmt.executeUpdate();
            return rowsaffected > 0;
        }
    }

    // Insert a new lender
    public Boolean insertLender(Lender lender) throws SQLException {
        String sql = "INSERT INTO lenders (username, city, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lender.getUsername());
            stmt.setString(2, lender.getCity());
            stmt.setString(3, lender.getPassword());
            
            int rowsaffected = stmt.executeUpdate();
            return rowsaffected > 0;
        }
    }

    // Get all renters
    public List<Renter> getAllRenters() throws SQLException {
        List<Renter> renters = new ArrayList<>();
        String sql = "SELECT * FROM renters";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Renter renter = new Renter(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("city")
                );
                renters.add(renter);
            }
        }
        return renters;
    }

    // Get all lenders
    public List<Lender> getAllLenders() throws SQLException {
        List<Lender> lenders = new ArrayList<>();
        String sql = "SELECT * FROM lenders";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Lender lender = new Lender(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("city")
                );
                lenders.add(lender);
            }
        }
        return lenders;
    }

    public Renter findRenterByUsername(String username,String password) throws SQLException {
        String sql = "SELECT * FROM renters WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Renter(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("city")
                    );
                }
            }
        }
        return null;
    }

    // // Find lender by username (for login)
    public Lender findLenderByUsername(String username,String password) throws SQLException {
        String sql = "SELECT * FROM lenders WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Lender(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("city")
                    );
                }
            }
        }
        return null;
    }

    // Optional: Close connection
    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
