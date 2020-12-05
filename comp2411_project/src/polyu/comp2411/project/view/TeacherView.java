package polyu.comp2411.project.view;

import polyu.comp2411.project.controller.AccountManager;
import polyu.comp2411.project.controller.PerformanceAnalysis;
import polyu.comp2411.project.controller.TestDesigner;
import polyu.comp2411.project.dao.ClasseDAO;
import polyu.comp2411.project.dao.impl.ClasseDAOImpl;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.StudentAnswer;
import polyu.comp2411.project.service.ManualJudgeService;
import polyu.comp2411.project.service.impl.ManualJudgeServiceImpl;
import polyu.comp2411.project.service.impl.PerformanceAnalysisServiceImpl;

import java.util.*;

public class TeacherView {
    public void teacherView(){
        System.out.println("**Welcome to Teacher System!**");
        System.out.println("********************************");
        System.out.println("1: Arrange New Exam");
        System.out.println("2: Manually Judgement");
        System.out.println("3: Students' Grades");
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
                TestDesigner testDesigner=new TestDesigner();
                testDesigner.createQuestions();
            }
                //accountManager.login();
            else if(op == 2){
                ManualJudgeService manualJudge=new ManualJudgeServiceImpl();
                int testId;
                System.out.println("Please input the test id you would like judge:");
                testId = sc.nextInt();
                sc.nextLine();
                ExamDAOImpl examDAO=new ExamDAOImpl();
                Exam ex= examDAO.searchByID(testId);
                List<StudentAnswer> studentAnswerList=manualJudge.fetchFLofAnExam(ex);
                for(StudentAnswer studentAnswer:studentAnswerList){
                    System.out.println("********************************");
                    System.out.println("Below is the student's answer of question "+ studentAnswer.getQueNo()+":");
                    System.out.println(studentAnswer.getAnswer());
                    System.out.println("Please enter the score of this answer");
                    int score = sc.nextInt();
                    sc.nextLine();
                    manualJudge.manualJudgeAQuesion(studentAnswer,score);
                }
                System.out.println("********************************");
            }
            else if(op == 3){
                System.out.println("Please enter the id of the class you want to see:");
                int id=sc.nextInt();
                sc.nextLine();
                System.out.println("********************************");
                ClasseDAOImpl classeDAO= new ClasseDAOImpl();
                Classe cls=classeDAO.searchById(id);
                PerformanceAnalysisServiceImpl performanceAnalysisService=new PerformanceAnalysisServiceImpl();
                Map<String, Double> subjectAvgs=performanceAnalysisService.subjectAvgs(cls);
                System.out.println("Below are the preformance analysis of the class:");
                System.out.println(subjectAvgs);
            }
            
        }catch (Exception e){
            System.out.println("Error: "+e+"please contact admin!");
        }
    }
    public static void main(String[] args) {
        TeacherView teacherView = new TeacherView();
        teacherView.teacherView();
    }
}
