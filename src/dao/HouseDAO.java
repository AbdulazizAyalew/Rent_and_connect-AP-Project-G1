package dao;

import db.DatabaseConnection;
import models.HouseListing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseDAO {
    private Connection conn;
    public HouseDAO() throws SQLException {
        conn = DatabaseConnection.getConnection();
    }
    // Insert a new house listing
    public Boolean addHouseListing(HouseListing house) {
        String sql = "INSERT INTO house_listings (title, description, location, price, house_type, availability) VALUES (?, ?, ?, ?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, house.getListingTitle());
            stmt.setString(2, house.getListingDescription());
            stmt.setString(3, house.getLocation());
            stmt.setBigDecimal(4, house.getPrice());
            stmt.setString(5, house.getHouseType());
            stmt.setBoolean(6, house.isAvailable());

            int row_affected = stmt.executeUpdate();
        
            return row_affected > 0;
        }
        catch(SQLException e){

        }
        return false;
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
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("house_type"),
                    rs.getString("location"),
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
