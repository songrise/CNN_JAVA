package polyu.comp2411.project.service;


import polyu.comp2411.project.dao.AccountDAO;
import polyu.comp2411.project.dao.impl.AccountDAOImpl;
import polyu.comp2411.project.entity.Account;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * provide service for account management.
 */
public class AccountManager {
    static final int INVALID = -1;
    static final int MINLEN = 6;

    //===========public===========
    public static int login(int uid,String inputPassword){
        if (inputPassword==null || inputPassword.length()<MINLEN)
            throw new IllegalStateException("Password must be no less than 6 bytes!");
        try{
            AccountDAO ad = new AccountDAOImpl();
            Account acct = ad.searchByID(uid); //search that account in DB
            String encryptedInput = stringToMD5(inputPassword);
            if (encryptedInput.equals(acct.getPassword())){//check if password is right
                return acct.getPrivilege(); // succeccful log in
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return INVALID;
    }

    public static void changePassword(){

    }

    public static void register(int uid, String inputPassword,int priviledge){
        if (inputPassword==null || inputPassword.length() < MINLEN)
            throw new IllegalStateException("Password must be no less than 6 bytes!");
        try{
            AccountDAO ad = new AccountDAOImpl();
            String encryptedInput = stringToMD5(inputPassword);
            Account acct = new Account(uid,encryptedInput,priviledge);
            ad.addAccount(acct);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //===========private===========


    private static String stringToMD5(String plainText) { //encrypt password
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
