package polyu.comp2411.project.controller;

import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.dao.impl.ScoreListDAOImpl;
import polyu.comp2411.project.dao.impl.StudentDAOImpl;
import polyu.comp2411.project.dao.ScoreListDAO;
import java.util.Scanner;

/**
 * students use this to view his score(letter grade) of all the exams
 */
public class ShowScoreList {
    public void showScoreList(){
        Scanner sc=new Scanner(System.in);
        StudentDAO student=new StudentDAOImpl();
        int stuId;
        System.out.printf("Please input your student id: ");
        stuId = sc.nextInt();
        sc.nextLine();
        Student stu=student.searchByID(stuId);
        ScoreListDAO scoreList=new ScoreListDAOImpl();
        System.out.println(scoreList.searchByStudent(stu));
    }
}
