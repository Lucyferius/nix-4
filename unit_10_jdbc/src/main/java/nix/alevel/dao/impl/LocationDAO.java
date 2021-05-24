package nix.alevel.dao.impl;

import nix.alevel.dao.BaseEntityDAO;
import nix.alevel.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements BaseEntityDAO<Location> {
    private static final Logger log = LoggerFactory.getLogger(LocationDAO.class);
    private Connection connection;
    public LocationDAO(Connection c){
        connection = c;
    }

    @Override
    public List<Location> read() {
        log.info("Start reading all locations");
        List<Location> locations = new ArrayList<>();
        try (PreparedStatement readAll = connection.prepareStatement("SELECT * FROM location")) {
            ResultSet resultSet = readAll.executeQuery();
            while (resultSet.next()){
                locations.add(new Location(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("Read all locations");
        return locations;
    }

    /*

    ---- Для дальнейшего совершенствования----
    ---- И саморазвития----


    public Location read(int id) {
        log.info("Start reading location with id: " + id);
        try (PreparedStatement readById = connection.prepareStatement("SELECT * FROM route WHERE id = ?")) {
            readById.setInt(1, id);
            ResultSet resultSet = readById.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                log.info("Read location with id:" + id + " name: " + name);
                return new Location(id, name);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("Location with id: "+ id +" doesn`t found");
        return null;
    }




    public void update(int id, Location location) {
        log.info("Start updating location with id: " + id);
        try (PreparedStatement update = connection.prepareStatement("UPDATE location SET name = ? WHERE id = ?")){
            update.setString(1, location.getName());
            update.setInt(2, id);
            update.execute();
            log.info("Solution with id: "+ id +" name: "+location.getName());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }



    public void create(Location e) {
        try(PreparedStatement create = connection.prepareStatement("INSERT IGNORE INTO Location name VALUES ?")) {
            create.setString(1, e.getName());
            create.execute();
            ResultSet generatedKeys = create.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    */

    @Override
    public void update(int id, Location location) {
    }

}
