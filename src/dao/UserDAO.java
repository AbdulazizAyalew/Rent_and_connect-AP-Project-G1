package dao;

import db.DatabaseConnection;
import java.sql.*;
import models.Lender;
import models.Renter;

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
        String sql = "INSERT INTO lenders (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lender.getUsername());
            stmt.setString(2, lender.getPassword());
            
            int rowsaffected = stmt.executeUpdate();
            return rowsaffected > 0;
        }
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
                        rs.getString("city"),
                        rs.getString("password")
                    );
                }
            }
        }
        return null;
    }

    // // Find lender by username (for login)
    public Lender findLenderByUsername(String username,String password) throws SQLException {
        String sql = "SELECT * FROM lenders WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Lender(
                        rs.getString("username"),
                        "Ethiopia",
                        rs.getString("password")
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
