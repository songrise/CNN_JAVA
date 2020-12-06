package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.*;
import polyu.comp2411.project.dao.impl.*;
import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.service.TestDesignerService;
import polyu.comp2411.project.util.TransactionUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
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
        } catch (DAOException de){
            System.out.println("DAO failed because: "+ de.getMessage());
        }
        catch (Exception e){
            TransactionUtil.rollBack();
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
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
        }

        catch (Exception e){
            e.printStackTrace();
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());
        }finally {
            TransactionUtil.closeConn();
        }

    }

    private BigDecimal convertToMs(int duration){
        try {
        BigDecimal durationInMs =BigDecimal.valueOf(duration);
        durationInMs = durationInMs.multiply(BigDecimal.valueOf(60000));
            return durationInMs;
        }
        catch (Exception e){
                throw new ServiceException(e.getMessage());
        }
    }

    private Timestamp convertToTimestamp(String dateStr){
        try {
            Timestamp tm = Timestamp.valueOf(dateStr);
            return tm;
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        TestDesignerService testDesignerService = new TestDesignerServiceImpl();
//        testDesignerService.createTest(1,1,1,30,"2020-12-08 00:30:01");
        testDesignerService.createQuestion(1,"1+2=",true,"FB","3",20);
        testDesignerService.createQuestion(1,"2*2=",true,"FB","4",20);
    }

}
