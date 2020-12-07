package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.LoggerDAO;
import polyu.comp2411.project.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoggerDAOImpl extends BaseDAO implements LoggerDAO {
    private Account account;
    private ResultSet rs;

    private ResultSet result;

    public LoggerDAOImpl() {
        super();
    }

    public LoggerDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void addLog(String info) {
        String sql = "INSERT INTO LOGGER VALUES(?,?)";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            int id = getNextId();
            ps.setInt(1, id);
            ps.setString(2, info);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();

            // rs.close();
        }
    }

    @Override
    public List<String> getAll() {
        String sql = "SELECT * FROM LOGGER";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            List<String> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getString("EVENT"));
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
            // rs.close();
        }
    }

    private int getNextId() {
        String sql = "SELECT MAX(LOG_ID) FROM LOGGER";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAX(LOG_ID)") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();

            // rs.close();
        }
        return -1;
    }
}
