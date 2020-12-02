package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.dao.ExamListDAO;
import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.dao.impl.ExamListDAOImpl;
import polyu.comp2411.project.dao.impl.QuestionDAOImpl;
import polyu.comp2411.project.dao.impl.StudentDAOImpl;
import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.service.TestDesignerService;
import polyu.comp2411.project.util.TransactionUtil;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Timestamp;

/**
 * provide service for teacher to Design a exam
 */
public class TestDesignerServiceImpl implements TestDesignerService {


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
    @Override
    public int createTest(Teacher arranger, Classe cls, Subject sub, BigInteger testDuration, Timestamp startTime){
        try{
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            ExamDAO examDAO = new ExamDAOImpl(conn);
            int testId = examDAO.getNextExamId();
            Exam newlyCreatedTest = new Exam(testId,testDuration,startTime,cls.getClassNo(),sub.getId(),arranger.getId());
            examDAO.addExam(newlyCreatedTest);
            //next add this exam to students' exam list
            StudentDAO studentDAO = new StudentDAOImpl(conn);
            ExamListDAO examListDAO = new ExamListDAOImpl(conn);
            for (Student stu : studentDAO.searchByClass(cls)){ // for all student in that class
                ExamList el = new ExamList(stu.getId(),testId);
                examListDAO.addExamList(el);
            }
            TransactionUtil.commit();
            return testId;
        }catch (Exception e){
            TransactionUtil.rollBack();
            e.printStackTrace();
        }finally {
            TransactionUtil.closeConn();
        }
        return -1;
    }

    /**
     * create a question and add it to an exam.
     * @param ex
     * @param qDesc: description of question
     * @param answer: correct answer
     * @return the No. of question
     */
    @Override
    public int createQuestion(Exam ex, String qDesc, boolean isCompulsory, String type, String answer, int score){
        try {
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            QuestionDAO questionDAO = new QuestionDAOImpl(conn);
            int qNo = questionDAO.getNextQuestionNo(ex);
            Question newQ = new Question(qNo, ex.getTestId(),qDesc,isCompulsory,type,answer,score);
            questionDAO.addQuestion(newQ);
            TransactionUtil.commit();
            return qNo;
        }catch (Exception e){
            e.printStackTrace();
            TransactionUtil.rollBack();
        }finally {
            TransactionUtil.closeConn();
        }
        return -1;
    }

}
