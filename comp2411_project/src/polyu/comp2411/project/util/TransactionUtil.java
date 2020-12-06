package polyu.comp2411.project.util;

import java.sql.*;

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

        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        // Database credentials
        final String USER = "root";
        final String PASS = "";
        try {
            // STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false); // commit when close the conn
            return conn;
        } catch (Exception se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
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
                conn = null;
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = TransactionUtil.getConn();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM TEACHER");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("TEACHER_NAME"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
