package polyu.comp2411.project.view;

import polyu.comp2411.project.controller.AccountManager;
import polyu.comp2411.project.controller.ExamListDisplay;
import polyu.comp2411.project.controller.ExamSystem;
import polyu.comp2411.project.controller.ScoreListDisplay;
import polyu.comp2411.project.dao.LoggerDAO;
import polyu.comp2411.project.dao.impl.DAOException;
import polyu.comp2411.project.dao.impl.LoggerDAOImpl;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.util.LoggerUtil;
import polyu.comp2411.project.util.TransactionUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    int uid;

    public AdminView(final int uid) {
        this.uid = uid;
        LoggerUtil.addLog("[Admin "+uid+"] log in system");
    }

    public void view(){
        System.out.println("**Welcome to Admin System!**");
        System.out.println("****************************");
        System.out.println("1: Change User password");
        System.out.println("2: View system log");

        System.out.println("****************************");
        Scanner sc = new Scanner(System.in);
        int op = -1;
        while (op !=1 && op !=2){
            System.out.print("Please input your option:");
            op = sc.nextInt();
            sc.nextLine();
        }
        try {
            if (op == 1){
                AccountManager accountManager = new AccountManager();
                accountManager.changePsw();
                view();
            }

            else if(op == 2){
                LoggerDAO loggerDAO = new LoggerDAOImpl();
                List<String> logs = loggerDAO.getAll();
                int count = 0;
                System.out.println("*************Logs************");
                for (String info :logs){
                    System.out.println(info);
                    if (++count == 10){
                        System.out.print("See more? (y/n): ");
                        if (sc.nextLine().toUpperCase().equals("N")){
                            break;
                        }
                    }
                }
                System.out.println("****************************");

                view();
            }

        }catch (DAOException | ServiceException e){
            System.out.println("Error: "+e +" please contact admin!");
            view();
        }
    }


    public int getUid() {
        return uid;
    }

    public void setUid(final int uid) {
        this.uid = uid;
    }
}
