package polyu.comp2411.project.controller;

import polyu.comp2411.project.dao.impl.DAOException;
import polyu.comp2411.project.service.AccountService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.service.impl.AccountServiceImpl;

import java.util.Scanner;

public class AccountManager {
    public int login() {
        final int MAX_ATTEMPT = 3;
        int uid;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Please input your id number(6 digits): ");
            try {
                String input = sc.nextLine().trim();
                if (input.length() == 6) {
                    uid = Integer.parseInt(input);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("The id should be 6 digits!");
        }

        AccountService accountService = new AccountServiceImpl();
        for (int i = 0; i < MAX_ATTEMPT; i++) {
            try {
                System.out.print("Please input your password: ");
                String pswd = sc.nextLine();

                int pri = accountService.login(uid, pswd);
                if (pri == -1) {
                    System.out.println("Wrong password, " + (MAX_ATTEMPT - i) + " times left!");
                    continue;
                }
                return uid;

            } catch (ServiceException | DAOException se) {
                System.out.println("Error: " + se);
            }
        }
        return -1;
    }

    public void register() {
        try {
            AccountService accountService = new AccountServiceImpl();
            int uid;
            String pswd, rePswd;
            Scanner sc = new Scanner(System.in);
            int priviledge = -1;
            final int NBR_ADMIN = 100000, NBR_TEACHER = 300000, NBR_STUDENT = 999999;

            while (true) {
                System.out.print("Please input your id number(6 digits): ");
                try {
                    String input = sc.nextLine().trim();
                    if (input.length() == 6) {
                        uid = Integer.parseInt(input);
                        break;
                    }
                } catch (NumberFormatException ignored) {
                }
                System.out.println("The id should be 6 digits!");
            }

            while (true) {
                System.out.print("Please input your password (at least 6 letters): ");
                pswd = sc.nextLine();

                if (pswd.length() < 6) {
                    System.out.println("Password must be no less than 6 bytes!");
                    continue;
                }

                System.out.print("Please input your password again (at least 6 letters): ");
                rePswd = sc.nextLine();

                if (pswd.equals(rePswd)) {
                    break;
                }
                System.out.println("two inputs not match!");
            }

            if (uid > 0) {
                if (uid < NBR_ADMIN)
                    priviledge = 0;
                else if (uid < NBR_TEACHER)
                    priviledge = 1;
                else
                    priviledge = 2;
            }
            accountService.register(uid, pswd, priviledge);

            // sc.close();

        } catch (ServiceException | DAOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void changePsw() {
        AccountService accountService = new AccountServiceImpl();
        Scanner sc = new Scanner(System.in);
        int uid;

        while (true) {
            System.out.print("Please input your id number(6 digits): ");
            try {
                String input = sc.nextLine().trim();
                if (input.length() == 6) {
                    uid = Integer.parseInt(input);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("The id should be 6 digits!");
        }

        String pswd, rePswd;
        while (true) {
            System.out.print("Please input new password (at least 6 letters): ");
            pswd = sc.nextLine();

            if (pswd.length() < 6) {
                System.out.println("Password must be no less than 6 bytes!");
                continue;
            }

            System.out.print("Please input your password again (at least 6 letters): ");
            rePswd = sc.nextLine();

            if (pswd.equals(rePswd)) {
                break;
            }
            System.out.println("two inputs not match!");
        }

        while (true) {
            System.out.print("Are you sure to reset the password as " + pswd + "(y/n): ");
            String input = sc.nextLine().trim().toUpperCase();
            if (input.equals("Y"))
                accountService.changePassword(uid, pswd);
            else if (!input.equals("N")) {
                continue;
            }
            break;
        }
    }

    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
//        accountManager.register();
        int i = accountManager.login();
        if (i == 0) {
            System.out.println("Welcome, admin!");
        }
    }
}
