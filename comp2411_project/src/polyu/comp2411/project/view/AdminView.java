package polyu.comp2411.project.view;

import polyu.comp2411.project.controller.AccountManager;
import polyu.comp2411.project.dao.LoggerDAO;
import polyu.comp2411.project.dao.impl.DAOException;
import polyu.comp2411.project.dao.impl.LoggerDAOImpl;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.util.LoggerUtil;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    private int uid;

    public AdminView(final int uid) {
        this.uid = uid;
        LoggerUtil.addLog("[Admin " + uid + "] log in system");
    }

    public void view() {
        System.out.println("**Welcome to Admin System!**");
        System.out.println("****************************");
        System.out.println("1: Change User password");
        System.out.println("2: View system log");
        System.out.println("****************************");

        Scanner sc = new Scanner(System.in);
        int op = -1;
        do {
            System.out.print("please indicate your option: ");
            try {
                String inpput = sc.nextLine().trim();
                if (inpput.length() == 1)
                    op = Integer.parseInt(inpput);
            } catch (NumberFormatException e) {
                System.out.println("please enter an integer");
            }
        } while (op != 1 && op != 2);

        try {
            if (op == 1) {
                AccountManager accountManager = new AccountManager();
                accountManager.changePsw();
            } else {
                LoggerDAO loggerDAO = new LoggerDAOImpl();
                List<String> logs = loggerDAO.getAll();
                int count = 0;

                System.out.println("*************Logs************");
                for (String info : logs) {
                    System.out.println(info);
                    if (++count == 10) {
                        boolean readmore = false;

                        while (true) {
                            System.out.print("See more? (y/n): ");
                            String input = sc.nextLine().trim().toUpperCase();
                            if (input.equals("Y"))
                                readmore = true;
                            else if (!input.equals("N")) {
                                continue;
                            }
                            break;
                        }

                        if (!readmore) {
                            break;
                        }
                    }
                }
                System.out.println("****************************");
            }
        } catch (DAOException | ServiceException e) {
            System.out.println("Error: " + e + " please contact admin!");
        }
        view();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(final int uid) {
        this.uid = uid;
    }
}
