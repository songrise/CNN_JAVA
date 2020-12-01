package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.misc.TransactionUtil;

import java.sql.*;

public class BaseDAO {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement ps;

    BaseDAO(){
        conn = TransactionUtil.getConn();
    }
    BaseDAO(Connection connection){
        this.conn = connection;
    }

    public Connection getConn() {
        return conn;
    }



    public void setStmt() {
        try {
            if (conn!=null)
                stmt=conn.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closeStatement();
            closePreparedStatement();
        }
    }
    public void setPs(String sql){
        try {
            if (conn!=null)
                ps = conn.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closeStatement();
            closePreparedStatement();
        }
    }
    public PreparedStatement getPs(){
        return ps;
    }

    public Statement getStmt() {
        return stmt;
    }


    public void closeStatement(){
        try {
            if (stmt!= null)
                stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closePreparedStatement(){
        if (ps!= null){
            try {
                ps.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
