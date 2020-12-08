package polyu.comp2411.project.view;

import polyu.comp2411.project.controller.AccountManager;
import polyu.comp2411.project.dao.impl.DAOException;
import polyu.comp2411.project.service.ServiceException;

import java.util.Scanner;

public class LoginView {
    private int uid = -1;

    public void view() {
        AccountManager accountManager = new AccountManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("**Welcome to Automated Exam System!**");
        System.out.println("**********************************");
        System.out.println("1: Login");
        System.out.println("2: Register");
        System.out.println("**********************************");

        int op = -1;
        do {
            System.out.print("please indicate your option: ");
            try {
                String input = sc.nextLine().trim();
                if (input.length() == 1)
                    op = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("please enter an integer");
            }
        } while (op != 1 && op != 2);

        try {
            if (op == 1)
                uid = accountManager.login();
            else {
                accountManager.register();
                System.out.println("Successfully registered.");
                view();
            }
        } catch (DAOException | ServiceException e) {
            System.out.println("Error: " + e + " please contact admin!");
            view();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e + ", program terminated, please contact admin!");
        }

//        finally {
//            sc.close();
//        }
    }

    public int getUid() {
        return uid;
    }

    public int getPriviledge() {
        if (uid >= 0) {
            if (uid < 100000) {
                return 0;
            }
            if (uid < 300000) {
                return 1;
            }
            if (uid < 999999) {
                return 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LoginView login = new LoginView();
        login.view();
    }
}
