package polyu.comp2411.project.service;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.dao.ExamListDAO;
import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.dao.impl.ExamListDAOImpl;
import polyu.comp2411.project.dao.impl.QuestionDAOImpl;
import polyu.comp2411.project.dao.impl.StudentDAOImpl;
import polyu.comp2411.project.entity.*;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * provide service for teacher to Design a exam
 */
public class TestDesignerService {


    /**
     * create test has following to steps: create a new Test entity and write it to DB.
     * for all student in that class, add this exam to their exam list. This method only
     * create a empty exam with no questions.
     * @param arranger
     * @param cls
     * @param sub
     * @param testDuration
     * @param startTime
     * @return id of the test
     */
    public int createTest(Teacher arranger, Classe cls, Subject sub, BigInteger testDuration, Timestamp startTime){
        ExamDAO examDAO = new ExamDAOImpl();
        int testId = examDAO.getNextExamId();
        Exam newlyCreatedTest = new Exam(testId,testDuration,startTime,cls.getClassNo(),sub.getId(),arranger.getId());
        examDAO.addExam(newlyCreatedTest);
        //next add this exam to students' exam list
        StudentDAO studentDAO = new StudentDAOImpl();
        ExamListDAO examListDAO = new ExamListDAOImpl();
        for (Student stu : studentDAO.searchByClass(cls)){ // for all student in that class
            ExamList el = new ExamList(stu.getId(),testId);
            examListDAO.addExamList(el);
        }
        return testId;
    }

    /**
     * create a question to an exam.
     * @param ex
     * @param qDesc: description of question
     * @param answer: correct answer
     * @return the No. of question
     */
    public int createQuestion(Exam ex,String qDesc, boolean isCompulsory, String type, String answer, int score){
        QuestionDAO questionDAO = new QuestionDAOImpl();
        int qNo = questionDAO.getNextQuestionNo(ex);
        Question newQ = new Question(qNo, ex.getTestId(),qDesc,isCompulsory,type,answer,score);
        questionDAO.addQuestion(newQ);
        return qNo;
    }
}
