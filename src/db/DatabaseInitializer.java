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
    try (Connection dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rent_connect", "root", "12345678")) {
        Statement dbStmt = dbConn.createStatement();

        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS renters (id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(100), email VARCHAR(100),phone_no VARCHAR(15),password VARCHAR(255), city VARCHAR(100))");
        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS lenders (id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(100), email VARCHAR(100),password VARCHAR(255),city VARCHAR(100))");
        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS house_listings (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(100), description TEXT,house_type VARCHAR(50),location VARCHAR(255),price DECIMAL(10,2), lender_id INT,FOREIGN KEY (lender_id) REFERENCES lenders(id) ON DELETE CASCADE,availability BOOLEAN)");
        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS messages (id INT PRIMARY KEY AUTO_INCREMENT, sender VARCHAR(50), receiver_id INT, content TEXT,sent_at DATETIME DEFAULT CURRENT_TIMESTAMP)");

        System.out.println("✅ Database and tables created successfully!");
    }

} catch (Exception e) {
    e.printStackTrace();
}

    }
}

