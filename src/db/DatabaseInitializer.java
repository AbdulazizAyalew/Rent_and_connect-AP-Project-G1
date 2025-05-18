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

        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS renters (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), email VARCHAR(100))");
        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS lenders (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), email VARCHAR(100))");
        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS house_listings (id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(100), description TEXT, lender_id INT)");
        dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS messages (id INT PRIMARY KEY AUTO_INCREMENT, sender_id INT, receiver_id INT, content TEXT)");

        System.out.println("✅ Database and tables created successfully!");
    }

} catch (Exception e) {
    e.printStackTrace();
}

    }
}
