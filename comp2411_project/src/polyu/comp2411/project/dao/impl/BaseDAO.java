package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.util.TransactionUtil;

import java.sql.*;

public class BaseDAO {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement ps;
//    private ResultSet rs;

    BaseDAO() {
        conn = TransactionUtil.getConn();
    }

    BaseDAO(Connection connection) {
        this.conn = connection;
    }

    public Connection getConn() {
        return conn;
    }

    public void setStmt() {
        try {
            if (conn != null)
                stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPs(String sql) {
        try {
            if (conn != null)
                ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void closeStatement() {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closePreparedStatement() {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = TransactionUtil.getConn();
            BaseDAO baseDAO = new BaseDAO(connection);
            baseDAO.setPs("SELECT * FROM TEACHER");
            ResultSet rs = baseDAO.getPs().executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("TEACHER_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
