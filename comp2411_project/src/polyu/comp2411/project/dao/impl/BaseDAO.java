package polyu.comp2411.project.dao.impl;

import java.sql.*;

public class BaseDAO {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement ps;

    BaseDAO(){
        setConn();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn() {
         final String JDBC_DRIVER = "com.mysql.jdbc.cj.Driver";
         final String DB_URL = "jdbc:mysql://localhost/test";

        //  Database credentials
        final String USER = "root";
        final String PASS = "";
        this.conn = conn;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception se){
        //Handle errors for JDBC
        se.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
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
            closeConn();
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
            closeConn();
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

    public void closeConn(){
        try {
            if (conn!= null)
                conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
