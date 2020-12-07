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
import polyu.comp2411.project.util.LoggerUtil;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ExamSystem {
    /**
     * a student use this to sit an exam
     *
     * @param stuId
     */

    public static void sitExam(int stuId) {
        ExamService examService = new ExamServiceImpl();
        Scanner sc = new Scanner(System.in);

        int testId;
        while (true) {
            System.out.print("Please input the test ID: ");
            try {
                String input = sc.nextLine().trim();
                if (input.length() == 6) {
                    testId = Integer.parseInt(input);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("The id should be 6 digits!");
        }

        List<Question> allQuestion = examService.sitExam(stuId, testId);
        LoggerUtil.addLog("[Student " + stuId + "] take exam " + testId);

        for (Question q : allQuestion) {
            if (isTimeUp(testId) == true) {
                System.out.println("Exam ended, please wait us upload your answer...");
                AutoJudgeService autoJudgeService = new AutoJudgeServiceImpl();
                autoJudgeService.judgeAnExam(testId);
                break;
            }
            System.out.printf("QNo:%d Type:%s, Complsory:%s Score:%d \n", q.getqNo(), q.getType(), q.getCompulsory(),
                    q.getScore());
            System.out.println("****************************************");
            System.out.println(q.getqDescri());
            System.out.println("****************************************");

            String answer;
            while (true) {
                System.out.printf("Input your answer(in one line): ");
                answer = sc.nextLine();

                boolean nextQustion = false;
                while (true) {
                    System.out.println("Next question?(Y/N)");
                    String op = sc.nextLine().trim().toUpperCase();
                    if (op.equals("Y"))
                        nextQustion = true;
                    else if (!op.equals("N"))
                        continue;
                    break;
                }
                if (nextQustion) {
                    break;
                }
            }
            examService.answerAnQuestion(q, stuId, answer);
        }

        while (true) {
            System.out.println("You have answered all question, submit now?(Y/N)");
            String op = sc.nextLine().trim().toUpperCase();
            if (op.equals("Y")) {
                AutoJudgeService autoJudgeService = new AutoJudgeServiceImpl();
                autoJudgeService.judgeAnExam(testId);
                break;
            }
        }
    }

    private static boolean isTimeUp(int testId) {
        ExamDAO examDAO = new ExamDAOImpl();
        Exam ex = examDAO.searchByID(testId);
        Timestamp startTime = ex.getStartTime();
        long endTime = startTime.getTime() + ex.getTestDuration().longValue();
        LocalDate timer = LocalDate.now();
        long now = timer.toEpochDay();
        if (now > endTime) {
            return true;
        }
        return false;
    }

    public void answerAnQuestion(Question que, Student stu, String stuAnswerStr) {
    }
}
