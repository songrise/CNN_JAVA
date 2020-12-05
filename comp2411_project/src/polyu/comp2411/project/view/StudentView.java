package polyu.comp2411.project.view;

import polyu.comp2411.project.controller.ExamSystem;
import polyu.comp2411.project.controller.TestDesigner;
import polyu.comp2411.project.dao.impl.*;
import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.service.ManualJudgeService;
import polyu.comp2411.project.service.impl.ManualJudgeServiceImpl;
import polyu.comp2411.project.service.impl.PerformanceAnalysisServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentView {
    public void stuView(){
        System.out.println("**Welcome to Student System!**");
        System.out.println("********************************");
        System.out.println("1: Check the coming exam");
        System.out.println("2: Do the exam");
        System.out.println("3: My Grades");
        Scanner sc = new Scanner(System.in);
        System.out.println("********************************");
        int op = -1;
        while (op !=1 && op !=2 && op !=3){
            System.out.println("Please input again:");
            op = sc.nextInt();
            sc.nextLine();
        }
        try {
            if (op == 1){
                ExamListDAOImpl examListDAO=new ExamListDAOImpl();
                System.out.println("Please input your student id:");
                int stuId = sc.nextInt();
                sc.nextLine();
                StudentDAOImpl studentDAO=new StudentDAOImpl();
                Student stu=studentDAO.searchByID(stuId);
                List examList=examListDAO.searchByStudent(stu);
                System.out.println("********************************");
                System.out.println("Below are the coming exams:");
                System.out.println(examList);
            }
            
            else if(op == 2){
                ExamSystem examSystem=new ExamSystem();
            }
            else if(op == 3){
                System.out.println("Please enter your student id:");
                int id=sc.nextInt();
                sc.nextLine();
                System.out.println("********************************");
                int stuId = sc.nextInt();
                sc.nextLine();
                StudentDAOImpl studentDAO=new StudentDAOImpl();
                Student stu=studentDAO.searchByID(stuId);
                ScoreListDAOImpl scoreListDAO=new ScoreListDAOImpl();
                List scoreList=scoreListDAO.searchByStudent(stu);
                System.out.println("Below are your grdes");
                System.out.println(scoreList);
            }

        }catch (Exception e){
            System.out.println("Error: "+e+"please contact admin!");
        }
    }


    public static void main(String[] args) {
        StudentView studentView = new StudentView();
        studentView.stuView();
    }
}
