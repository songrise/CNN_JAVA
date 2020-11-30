package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Account;

public interface AccountDAO {
    Account searchByID(int id);
    void addAccount(Account act);
    void delAccount(Account act);
    void updAccount(Account act, Account newAct);
    void changePassword(Account act);
}
