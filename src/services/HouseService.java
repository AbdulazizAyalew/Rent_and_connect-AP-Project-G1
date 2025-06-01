package services;

import dao.HouseDAO;
import java.sql.SQLException;
import java.util.List;
import models.HouseListing;

public class HouseService {

    private HouseDAO houseDAO;
 
    public HouseService() throws SQLException {
        houseDAO = new HouseDAO();
    }   

    public Boolean addHouse(HouseListing house) throws SQLException {
        return houseDAO.addHouseListing(house);
    }

    public List<HouseListing> getAllAvailableHouses() throws SQLException {
        return houseDAO.getAllHouses();
    }

    
}
