package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection(); 
     Statement stmt = conn.createStatement()) {

    // Create database first
    stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS rent_connect");

    // Now connect to that DB and create tables
    try (Connection dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3307/rent_connect", "root", "12345678")) {
        Statement dbStmt = dbConn.createStatement();

        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS renters (renter_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(100), password VARCHAR(255), city VARCHAR(100), registration_day DATETIME DEFAULT CURRENT_TIMESTAMP)");
        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS lenders (lender_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(100), phone_no VARCHAR(15), password VARCHAR(255), joined_date DATETIME DEFAULT CURRENT_TIMESTAMP)");
        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS house_listings (house_id INT AUTO_INCREMENT PRIMARY KEY,lender_id INT, title VARCHAR(150), description TEXT, image_path VARCHAR(255), house_type VARCHAR(50), location VARCHAR(255), price DECIMAL(10,2), availability BOOLEAN, FOREIGN KEY (lender_id) REFERENCES lenders(lender_id) ON DELETE CASCADE, created_at DATETIME DEFAULT CURRENT_TIMESTAMP)");

        System.out.println(">> Database and tables created successfully!");
    }

} catch (Exception e) {
    e.printStackTrace();
}

    }
}

