package dao;

import db.DatabaseConnection;
import models.HouseListing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseDAO {

    // Insert a new house listing
    public void addHouseListing(HouseListing house) {
        String sql = "INSERT INTO house_listings (title, description, address, price, lender_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, house.getListingTitle());
            stmt.setString(2, house.getListingDescription());
            stmt.setString(3, house.getLocation());
            stmt.setBigDecimal(4, house.getPrice());
            stmt.setInt(5, house.getLenderId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get house by ID
    public HouseListing getHouseById(int id) {
        String sql = "SELECT * FROM house_listings WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HouseListing house = new HouseListing(
                    rs.getInt("id"),
                    rs.getInt("lender_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("house_type"),
                    rs.getBigDecimal("price"),
                    rs.getBoolean("availability")
                    
                );
                return house;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all house listings
    public List<HouseListing> getAllHouses() {
        List<HouseListing> houses = new ArrayList<>();
        String sql = "SELECT * FROM house_listings";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                HouseListing house = new HouseListing(
                    rs.getInt("id"),
                    rs.getInt("lender_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("house_type"),
                    rs.getBigDecimal("price"),
                    rs.getBoolean("availability")
                );
                houses.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houses;
    }

    // You can add update and delete methods similarly...
}
