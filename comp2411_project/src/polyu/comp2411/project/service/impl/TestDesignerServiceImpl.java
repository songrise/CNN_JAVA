package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.*;
import polyu.comp2411.project.dao.impl.*;
import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.service.TestDesignerService;
import polyu.comp2411.project.util.TransactionUtil;

import java.math.BigDecimal;
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
     * @param arrangerID
     * @param clsID
     * @param subID
     * @param testDuration
     * @param startTime
     * @return id of the test
     */
    @Override
    public int createTest(int arrangerID, int clsID, int subID, int testDuration, String startTimeStr){

//        Classe cls;
//        Subject sub;
//        try {
//            Connection conn = TransactionUtil.getConn();
//            TransactionUtil.startTransaction();
//
//        }

        try{
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            ExamDAO examDAO = new ExamDAOImpl(conn);
            ClasseDAO classeDAO = new ClasseDAOImpl(conn);
            StudentDAO studentDAO = new StudentDAOImpl(conn);
            ExamListDAO examListDAO = new ExamListDAOImpl(conn);


            int testId = examDAO.getNextExamId();
            BigDecimal durationInMs = convertToMs(testDuration);
            Timestamp startTime = convertToTimestamp(startTimeStr);
            Exam newlyCreatedTest = new Exam(testId,durationInMs,startTime,clsID,subID, arrangerID);
            examDAO.addExam(newlyCreatedTest);

            //next add this exam to students' exam list
            Classe cls = classeDAO.searchById(clsID);
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
    public int createQuestion(int examID, String qDesc, boolean isCompulsory, String type, String answer, int score){
        try {
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            QuestionDAO questionDAO = new QuestionDAOImpl(conn);
            ExamDAO examDAO = new ExamDAOImpl(conn);
            Exam ex = examDAO.searchByID(examID);

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

    private BigDecimal convertToMs(int duration){
        BigDecimal durationInMs =BigDecimal.valueOf(duration);
        durationInMs = durationInMs.multiply(BigDecimal.valueOf(60000));
        return durationInMs;
    }

    private Timestamp convertToTimestamp(String dateStr){
        Timestamp tm = Timestamp.valueOf(dateStr);
        return tm;
    }

    public static void main(String[] args) {
        TestDesignerService testDesignerService = new TestDesignerServiceImpl();
        testDesignerService.createTest(1,1,1,30,"2020-12-08 00:00:01");
    }

}
