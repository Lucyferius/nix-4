package nix.alevel.dao.impl;

import nix.alevel.dao.BaseEntityDAO;
import nix.alevel.model.Solution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDAO implements BaseEntityDAO<Solution> {
    private static final Logger log = LoggerFactory.getLogger(LocationDAO.class);
    private Connection connection;
    public SolutionDAO(Connection c){
        connection = c;
    }

    @Override
    public List<Solution> read() {
        log.info("Start reading all solutions");
        List<Solution> solutions = new ArrayList<>();
        try (PreparedStatement readAll = connection.prepareStatement("SELECT * FROM solution")) {
            ResultSet resultSet = readAll.executeQuery();
            while (resultSet.next()){
                try {
                    if(resultSet.getString("cost")!=null)
                    solutions.add(new Solution(resultSet.getInt("problem_id"), resultSet.getInt("cost")));
                }catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("Read all solutions");
        return solutions;
    }

    public Solution read(int problemId) {
        log.info("Start reading solution with id: " + problemId);
        try (PreparedStatement readById = connection.prepareStatement("SELECT * FROM solution WHERE problem_id = ?")) {
            readById.setInt(1, problemId);
            ResultSet resultSet = readById.executeQuery();
            if (resultSet.next()){
                if(resultSet.getString("cost") !=null) {
                    int cost = Integer.parseInt(resultSet.getString("cost"));
                    log.info("Read solution with problemId:" + problemId + " cost:" + cost);
                    return new Solution(problemId, cost);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("Solution with problemId: "+ problemId +" doesn`t found");
        return null;
    }

    // Solution create with trigger
    // All problems write into table solution in column problem_id,
    // But column cost stay being null

    @Override
    public void update(int id, Solution solution) {
        log.info("Start updating solution with id: " + id);
        if(read(id)!=null) return;
        try (PreparedStatement update = connection.prepareStatement("UPDATE solution SET cost = ? WHERE problem_id = ?")){
            update.setInt(1, solution.getCost());
            update.setInt(2, id);
            update.execute();
            log.info("Solution with id: "+ id +" cost: "+solution.getCost());
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
