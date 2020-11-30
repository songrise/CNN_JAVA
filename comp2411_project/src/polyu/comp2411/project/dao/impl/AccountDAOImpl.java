package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.AccountDAO;
import polyu.comp2411.project.entity.Account;
import polyu.comp2411.project.entity.Student;

import java.sql.Connection;

public class AccountDAOImpl extends BaseDAO implements AccountDAO {
    Student stu;

    @Override
    public Account searchByID(int id) {
        return null;
    }

    @Override
    public void addAccount(Account act) {

    }

    @Override
    public void delAccount(Account act) {

    }

    @Override
    public void changePassword(Account act) {

    }
}
