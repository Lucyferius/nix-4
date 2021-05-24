package nix.alevel;

import nix.alevel.dao.impl.LocationDAO;
import nix.alevel.dao.impl.ProblemDAO;
import nix.alevel.dao.impl.RouteDAO;
import nix.alevel.dao.impl.SolutionDAO;
import nix.alevel.db.ConnectionHandler;
import nix.alevel.model.Location;
import nix.alevel.model.Problem;
import nix.alevel.model.Route;
import nix.alevel.model.Solution;
import nix.alevel.util.MinPathInGraphSearcher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionHandler.getConnection();
            LocationDAO locationDAO  = new LocationDAO(connection);
            RouteDAO routeDAO = new RouteDAO(connection);
            ProblemDAO problemDAO = new ProblemDAO(connection);
            SolutionDAO solutionDAO = new SolutionDAO(connection);

            List<Location> locations = locationDAO.read();
            List<Route> routes = routeDAO.read();
            List<Problem> problems = problemDAO.read();

            MinPathInGraphSearcher minPathInGraphSearcher = new MinPathInGraphSearcher(locations, routes);
            for (Problem problem: problems){
                int cost = minPathInGraphSearcher.determineShortestPath(problem.getIdFrom(), problem.getIdTo());
                Solution solution = new Solution(cost);
                solutionDAO.update(problem.getId(), solution);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
