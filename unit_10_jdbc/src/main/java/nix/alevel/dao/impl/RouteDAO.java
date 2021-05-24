package nix.alevel.dao.impl;

import nix.alevel.dao.BaseEntityDAO;
import nix.alevel.model.Location;
import nix.alevel.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO implements BaseEntityDAO<Route> {
    private static final Logger log = LoggerFactory.getLogger(LocationDAO.class);
    private Connection connection;
    public RouteDAO(Connection c){
        connection = c;
    }
    @Override
    public List<Route> read() {
        log.info("Start reading all routes");
        List<Route> routes = new ArrayList<>();
        try (PreparedStatement readAll = connection.prepareStatement("SELECT * FROM route")) {
            ResultSet resultSet = readAll.executeQuery();
            while (resultSet.next()){
                routes.add(new Route(resultSet.getInt("id"),
                            resultSet.getInt("id_from"),
                            resultSet.getInt("id_to"),
                            resultSet.getInt("cost")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("Read all routes");
        return routes;
    }

    @Override
    public void update(int id, Route route) {

    }

}
