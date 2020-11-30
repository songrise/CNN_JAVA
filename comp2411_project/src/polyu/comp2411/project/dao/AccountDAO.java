package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Account;

public interface AccountDAO {
    Account searchByID(int id);
    void addAccount(Account act);
    void delAccount(Account act);
    //upload an account the newAct shall have same user id as old one
    void updAccount(Account act, Account newAct);

}
