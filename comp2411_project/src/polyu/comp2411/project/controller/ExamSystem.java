package polyu.comp2411.project.controller;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.service.ExamService;
import polyu.comp2411.project.service.impl.ExamServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ExamSystem {
    /**
     * a student use this to sit an exam
     * @param stu
     */
    public void examSystem(Student stu, Exam ex){
        ExamService service = new ExamServiceImpl();
        List<Question> allQuestion = service.sitExam(stu, ex);
        for (Question q:allQuestion){//todo check time up
            System.out.println();//todo print a question
            String answer;
            // Scanner System.in TODO: get the student answer
//            service.answerAnQuestion(q,stu,answer);
        }
    }


    private boolean isTimeUp(){
        return false;
    }
}
