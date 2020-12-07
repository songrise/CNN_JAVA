package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.AccountDAO;
import polyu.comp2411.project.dao.impl.AccountDAOImpl;
import polyu.comp2411.project.entity.Account;
import polyu.comp2411.project.service.AccountService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.util.TransactionUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.NoSuchElementException;

/**
 * provide service for account management.
 */
public class AccountServiceImpl implements AccountService {
    static final int INVALID = -1;
    static final int MINLEN = 6;

    @Override
    public int login(int uid, String inputPassword) {
        if (inputPassword == null || inputPassword.length() < AccountServiceImpl.MINLEN)
            throw new ServiceException("Password must be no less than 6 bytes!");
        try {
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            AccountDAO dao = new AccountDAOImpl(conn);
            Account acct = dao.searchByID(uid); // search that account in DB
            String encryptedInput = AccountServiceImpl.stringToMD5(inputPassword);
            TransactionUtil.commit();
            if (encryptedInput.equals(acct.getPassword())) {// check if password is right
                return acct.getPrivilege(); // succeccful log in
            }
            return INVALID;
        }catch (NoSuchElementException e){
            throw new ServiceException(uid+ " did not registered");
        }
        catch (Exception e) {
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());
        } finally {
            TransactionUtil.closeConn();
        }
    }

    @Override
    public void register(int uid, String inputPassword, int priviledge) {
        if (inputPassword == null || inputPassword.length() < AccountServiceImpl.MINLEN)
            throw new ServiceException("Password must be no less than 6 bytes!");
        try {
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            AccountDAO dao = new AccountDAOImpl(conn);
            String encryptedInput = AccountServiceImpl.stringToMD5(inputPassword);
            Account acct = new Account(uid, encryptedInput, priviledge);
            dao.addAccount(acct);
            TransactionUtil.commit();
        } catch (Exception e) {
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());
        } finally {
            TransactionUtil.closeConn();
        }
    }

    @Override
    public void changePassword(int uid, String newPassword) {
        if (newPassword == null || newPassword.length() < AccountServiceImpl.MINLEN)
            throw new ServiceException("Password must be no less than 6 bytes!");
        try{
        Connection conn = TransactionUtil.getConn();
        TransactionUtil.startTransaction();
        AccountDAO dao = new AccountDAOImpl(conn);
        Account oldAcct = dao.searchByID(uid);

        String encryptedInput = AccountServiceImpl.stringToMD5(newPassword);
        Account acct = new Account(uid, encryptedInput, oldAcct.getPrivilege());

        dao.updAccount(oldAcct,acct);

        TransactionUtil.commit();
    } catch (Exception e) {
        e.printStackTrace();
        TransactionUtil.rollBack();
        throw new ServiceException(e.getMessage());
    } finally {
        TransactionUtil.closeConn();
    }
    }
    // ===========private===========

    /**
     * reference: https://www.cnblogs.com/zhuyeshen/p/11424713.html
     * 
     * @param plainText
     * @return encryped text
     */
    private static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
