package polyu.comp2411.project.service;

import polyu.comp2411.project.dao.AccountDAO;
import polyu.comp2411.project.dao.impl.AccountDAOImpl;
import polyu.comp2411.project.entity.Account;
import polyu.comp2411.project.service.impl.AccountServiceImpl;
import polyu.comp2411.project.util.TransactionUtil;

import java.sql.Connection;

public interface AccountService {
    //===========public===========

    /**
     * login into an account
     * @param uid uid of user, should be same as the id(stuId for student, teacherId for teacher)
     * @param inputPassword inputted password
     * @return priviledge, 0 for admin, 1 for teacher, 2 for student.
     */
    int login(int uid, String inputPassword);

    void register(int uid, String inputPassword, int priviledge);
    void changePassword();

}
