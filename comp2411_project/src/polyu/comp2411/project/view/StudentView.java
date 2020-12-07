package polyu.comp2411.project.view;

//import polyu.comp2411.project.controller.ExamSystem;

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
import java.util.Scanner;

public class StudentView {
    private int uid;

    public StudentView(final int uid) {
        this.uid = uid;
        LoggerUtil.addLog("[Student "+uid+"] log in system");
    }

    public void stuView(){
        System.out.println("**Welcome to Student System!**");
        System.out.println("******************************");
        System.out.println("1: Check the coming exam");
        System.out.println("2: Do the exam");
        System.out.println("3: My Grades");
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************");
        int op = -1;
        while (op !=1 && op !=2 && op !=3){
            System.out.print("Please input your option:");
            op = sc.nextInt();
            sc.nextLine();
        }
        try {
            if (op == 1){
                ExamListDisplay.showExamList(uid);
                stuView();
            }
            
            else if(op == 2){
                ExamSystem.sitExam(uid);
                stuView();
            }
            else if(op == 3){
                ScoreListDisplay.showScoreList(uid);
                stuView();
            }

        }catch (DAOException | ServiceException e){
            System.out.println("Error: "+e +" please contact admin!");
            stuView();
        }catch (Exception e){
            System.out.println("Unexpected error: "+e +", program terminated, please contact admin!");
        }
    }


    public static void main(String[] args) {
        StudentView studentView = new StudentView(1);
        studentView.stuView();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
