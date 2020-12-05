package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.AccountDAO;
import polyu.comp2411.project.entity.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOImpl extends BaseDAO implements AccountDAO {
    private Account account;
    private ResultSet rs;

    private ResultSet result;

    public AccountDAOImpl() {
        super();
    }

    public AccountDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Account searchByID(int id) {
        String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = ?";
        try {
            setPs(sql);
            getPs().setInt(1, id);
            rs = getPs().executeQuery();
            while (rs.next()) {
                String password = rs.getString("PASSWORD");
                int privilege = rs.getInt("PRIVILEGE");
                Account result = new Account(id, password, privilege);
                this.account = result; // set the teacher field as this.
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {

            closeStatement();
            closePreparedStatement();
        }
         return null;
    }

    @Override
    public void addAccount(Account act) {
        String sql = "INSERT INTO ACCOUNT VALUES(?,?,?)"; // parameter to be set later
        try {
            setPs(sql);
            // set parameter of sql
            getPs().setInt(1, act.getUid());
            getPs().setString(2, act.getPassword());
            getPs().setInt(3, act.getPrivilege());
            getPs().execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used

            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public void delAccount(Account act) {
        String sql = "DELETE FROM ACCOUNT WHERE USER_ID = ?"; // parameter to be set later
        try {
            setPs(sql);
            // set parameter of sql
            getPs().setInt(1, act.getUid());
            getPs().execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used

            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public void updAccount(Account act, Account newAct) {
        String sql = "UPDATE ACCOUNT SET USER_ID=?,PASSWORD=?,PRIVILEGE=? WHERE USER_ID = ?"; // parameter to be set
                                                                                              // later
        try {
            setPs(sql);
            // set parameter of sql
            getPs().setInt(1, newAct.getUid());
            getPs().setString(2, newAct.getPassword());
            getPs().setInt(3, newAct.getPrivilege());
            getPs().setInt(4, act.getUid());
            getPs().execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used

            closeStatement();
            closePreparedStatement();
        }
    }
}
