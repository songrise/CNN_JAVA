package polyu.comp2411.project.controller;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.service.AutoJudgeService;
import polyu.comp2411.project.service.ExamService;
import polyu.comp2411.project.service.impl.AutoJudgeServiceImpl;
import polyu.comp2411.project.service.impl.ExamServiceImpl;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ExamSystem {
    /**
     * a student use this to sit an exam
     * @param stuId
     *
     */


    public static void sitExam(int stuId){


        ExamService examService = new ExamServiceImpl();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please input the test ID: ");
        int testId = sc.nextInt();
        sc.nextLine();

        List<Question> allQuestion = examService.sitExam(stuId, testId);
        for (Question q:allQuestion){
            if(isTimeUp(testId)==true){
                System.out.println("Exam ended, please wait us upload your answer...");
                AutoJudgeService autoJudgeService = new AutoJudgeServiceImpl();
                autoJudgeService.judgeAnExam(testId);
                break;
            }
            System.out.printf("QNo:%d Type:%s, Complsory:%s Score:%d \n",q.getqNo(),q.getType(),q.getCompulsory(),q.getScore());
            System.out.println("****************************************");
            System.out.println(q.getqDescri());
            System.out.println("****************************************");
            System.out.printf("Input your answer(in one line): ");

            String answer;

            answer= sc.nextLine();
            examService.answerAnQuestion(q, stuId,answer);
        }
        System.out.println("You have answered all question, submit now?(Y/n)");
        String op = sc.nextLine();
        if(op.equals("Y")){
            AutoJudgeService autoJudgeService = new AutoJudgeServiceImpl();
            autoJudgeService.judgeAnExam(testId);
        }
    }


    private static boolean isTimeUp(int testId){
        ExamDAO examDAO = new ExamDAOImpl();
        Exam ex = examDAO.searchByID(testId);
        Timestamp startTime = ex.getStartTime();
        long endTime = startTime.getTime() + ex.getTestDuration().longValue();
        LocalDate timer = LocalDate.now();
        long now =timer.toEpochDay();
        if(now>endTime){
            return true;
        }
        return false;
    }

    public void answerAnQuestion(Question que, Student stu, String stuAnswerStr){
   }


}
