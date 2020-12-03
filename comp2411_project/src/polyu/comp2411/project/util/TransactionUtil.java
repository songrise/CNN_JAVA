package polyu.comp2411.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * provide utility for transaction control. todo I will refactor DAO part,
 * transfer the get connectoion part to here.
 */

public class TransactionUtil {
    private static Connection conn;

    public static Connection getConn() {
        if (conn!= null){// if it is connected
            return conn;
        }

        final String JDBC_DRIVER = "com.mysql.jdbc.cj.Driver";
        final String DB_URL = "jdbc:mysql://localhost/test";
        // Database credentials
        final String USER = "root";
        final String PASS = "";
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false); // commit when close the conn
            return conn;
        } catch (Exception se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // finally block used to close resources
            closeConn();
        } // end try
        return null;
    }

    public static void startTransaction(){
        if (conn == null) {
            getConn();
        }

        try {
            conn.setAutoCommit(false);
        }
        catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConn();
        }

    }

    public static void rollBack(){
        try {
            if (conn!=null){
                conn.rollback();
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public static void commit(){
        try {
            if (conn!= null){
                conn.commit();
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    public static void closeConn() {
        try {
            if (conn!= null){
                conn.close();
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}