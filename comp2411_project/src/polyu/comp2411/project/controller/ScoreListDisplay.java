package polyu.comp2411.project.controller;

import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.dao.SubjectDAO;
import polyu.comp2411.project.dao.impl.SubjectDAOImpl;
import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.dao.impl.ScoreListDAOImpl;
import polyu.comp2411.project.dao.impl.StudentDAOImpl;
import polyu.comp2411.project.dao.ScoreListDAO;
import polyu.comp2411.project.service.ScoreListService;
import polyu.comp2411.project.service.impl.ScoreListServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * students use this to view his score(letter grade) of all the exams
 */
public class ScoreListDisplay {
    public static void showScoreList(int stuid){
        Scanner sc=new Scanner(System.in);
        StudentDAO student=new StudentDAOImpl();
        Student stu = student.searchByID(stuid);
        System.out.println("Student "+stu.getName()+", here is your transcript: ");
        ScoreListService scoreListService = new ScoreListServiceImpl();
        List<ScoreList> sl = scoreListService.getScoreList(stuid);
        if (sl.isEmpty()){
            System.out.printf("Seems you have no exam record\n\n");
        }
        else {

            System.out.printf("Test ID\t\tGrade\t\tFeedback\n");
            for (ScoreList record: sl){
                System.out.printf("%06d\t\t%s\t\t%s\n",record.getTestId(),convertToLetterGrade(record.getScore()),record.getFeedBack());
            }
        }
    }

    private static String convertToLetterGrade(int grade){
        assert grade>=0 && grade<=100;
        if (grade<40){
            return "F";
        }
        else if(grade<45){
            return "D-";
        }else if(grade<50){
            return "D";
        }
        else if(grade<55){
            return "D+";
        }else if(grade < 60){
            return "C-";
        }else if(grade < 65){
            return "C";
        }else if(grade < 70){
            return "C+";
        }else if(grade < 75){
            return "B-";
        }else if(grade < 80){
            return "B";
        }else if(grade < 85){
            return "B+";
        }else if(grade < 90){
            return "A-";
        }else if(grade < 95){
            return "A";
        }else if(grade < 100){
            return "A+";
        }
        return "F";

    }

}

