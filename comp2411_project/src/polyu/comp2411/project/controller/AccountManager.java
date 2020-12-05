package polyu.comp2411.project.controller;

import polyu.comp2411.project.dao.impl.DAOException;
import polyu.comp2411.project.service.AccountService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.service.impl.AccountServiceImpl;

import java.util.Scanner;

public class AccountManager {
    public int login(){
        final int MAX_ATTEMPT = 3;
        int uid;
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input your id number(6 digits): ");
        uid = sc.nextInt();
        sc.nextLine();
        for (int i = 0;i<MAX_ATTEMPT;i++){
            try {
                System.out.println("Please input your password: ");
                String pswd = sc.nextLine();
                AccountService accountService = new AccountServiceImpl();
                int pri = accountService.login(uid,pswd);
                if (pri == -1){
                    System.out.println("Wrong password, "+(MAX_ATTEMPT - i)+" times left!");
                    continue;
                }
                return pri;
            }catch (ServiceException| DAOException se){
                System.out.println("Error: "+se);
            }
        }

        return  -1;
    }

    public void register(){
        try {
            AccountService accountService = new AccountServiceImpl();
            int uid;
            String pswd, rePswd;
            Scanner sc=new Scanner(System.in);
            int priviledge = -1;
            final int NBR_ADMIN = 100000, NBR_TEACHER = 300000, NBR_STUDENT = 999999;
            System.out.println("Please input your id number(6 digits): ");
            uid = sc.nextInt();
            sc.nextLine();

            System.out.println("Please input your password (at least 6 letters): ");
            pswd = sc.nextLine();
            System.out.println("Please input your password again (at least 6 letters): ");
            rePswd = sc.nextLine();

            if (uid>0){
                if (uid<NBR_ADMIN)
                    priviledge = 0;
                else if(uid<NBR_ADMIN)
                    priviledge = 1;
                else
                    priviledge = 2;
            }

            if (rePswd.equals(pswd)){
                accountService.register(uid,pswd,priviledge);
            }else {
                throw new ServiceException("two inputs not match!");
            }


            sc.close();

        }catch (ServiceException | DAOException e){
            System.out.println("Error: "+e);

        }

    }


    public void changePsw(){
        AccountService accountService = new AccountServiceImpl();
        accountService.changePassword();
    }

    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
//        accountManager.register();
        int i = accountManager.login();
        if (i == 0){
            System.out.println("Welcome, admin!");
        }
    }


}
