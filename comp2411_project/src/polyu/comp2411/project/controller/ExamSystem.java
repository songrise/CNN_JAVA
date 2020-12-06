package polyu.comp2411.project.controller;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.ExamList;
import polyu.comp2411.project.service.ExamService;
import polyu.comp2411.project.service.impl.ExamServiceImpl;



import java.util.List;
import java.util.Scanner;

public class ExamSystem {
    /**
     * a student use this to sit an exam
     * @param stu
     */


    public void sitExam(Student stu, Exam ex){
        if(!stu.ExamList.testId.contains(ex.testId)){
            exit;
        }

        ExamService service = new ExamServiceImpl();
        List<Question> allQuestion = service.sitExam(stu, ex);
        for (Question q:allQuestion){
            if(isTimeUp==true){
                break;
            }
            System.out.println(q.qNo);
            System.out.println(q.qDescri);
            System.out.println(q.type);
            System.out.println(q.score);
            if(q.compulsory==true){
                System.out.println("Compulsory");
            }
            else{
                System.out.println("Elective");
            }

            String answer;
            Scanner sc= new Scanner(System.in);
            answer= sc.nextline();
        }
    }


    private boolean isTimeUp(int testId);
    
    public void answerAnQuestion(Question que, Student stu, String stuAnswerStr){
        Scanner sc=new Scanner(System.in);       
   }
}
