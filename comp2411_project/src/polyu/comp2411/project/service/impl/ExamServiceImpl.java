package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.*;
import polyu.comp2411.project.dao.impl.*;
import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.service.ExamService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.util.TransactionUtil;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * provide service for student to have online test
 */
public class ExamServiceImpl implements ExamService {

    /**
     * sit in an exam with steps. 1) check if student can enter that exam
     * <p>
     * 2) if can, then insert all the question of this exam into the StudentAnswer
     * table for this student, and the answer is null(which means not answered at
     * the very begining of exam)
     *
     * @param stuId
     * @param testId
     * @return
     */
    @Override
    public List<Question> sitExam(int stuId, int testId) {

        try {
            if (!canEnterExam(stuId, testId)) {
                throw new IllegalAccessError("Student " + stuId + " has no access to " + testId + " now!");
            }
            // else that student can
            Connection conn = TransactionUtil.getConn();

            TransactionUtil.startTransaction();

            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);
            QuestionDAO questionDAO = new QuestionDAOImpl(conn);
            ExamDAO examDAO = new ExamDAOImpl(conn);

            List<Question> questionsInThisExam = questionDAO.searchByExam(examDAO.searchByID(testId));// all questions
            // in this exam
            for (Question q : questionsInThisExam) {
                // create an empty record for each question, and append that to student answer
                StudentAnswer emptyAnswer = new StudentAnswer(stuId, testId, q.getqNo(), null, 0);
                studentAnswerDAO.addStudentAnswer(emptyAnswer);
            }
            TransactionUtil.commit();

            // return the question list for student;

            return questionsInThisExam;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());

        } finally {
            TransactionUtil.closeConn();

        }

    }

    @Override
    public void answerAnQuestion(Question que, int stuId, String stuAnsStr) {
        if (que == null || stuAnsStr == null) {
            throw new IllegalArgumentException();
        }
        try {
            Connection conn = TransactionUtil.getConn();

            TransactionUtil.startTransaction();

            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);
            StudentDAO studentDAO = new StudentDAOImpl(conn);
            ExamDAO examDAO = new ExamDAOImpl(conn);
            Student student = studentDAO.searchByID(stuId);
            Exam exam = examDAO.searchByID(que.getTestId());

            StudentAnswer studentAnswer = studentAnswerDAO.searchByKey(student, exam, que);
            StudentAnswer newAnswer = (StudentAnswer) studentAnswer.clone();
            newAnswer.setAnswer(stuAnsStr);
            studentAnswerDAO.updStudentAnswer(studentAnswer, newAnswer);

            TransactionUtil.commit();

        } catch (Exception e) {
            e.printStackTrace();
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());

        } finally {
            TransactionUtil.closeConn();

        }
    }

    /**
     * judge wheather a student can enter an exam. A student can enter if he has
     * that exam in his exam list and current time is examination period.
     *
     * @param stu
     * @param ex
     * @return
     */
    private boolean canEnterExam(int stuId, int testId) {//todo
        try {
            Connection conn = TransactionUtil.getConn();

            TransactionUtil.startTransaction();

            StudentDAO studentDAO = new StudentDAOImpl(conn);
            ExamListDAO examListDAO = new ExamListDAOImpl(conn);
            ExamDAO examDAO = new ExamDAOImpl(conn);
            Student student = studentDAO.searchByID(stuId);
            Exam exam = examDAO.searchByID(testId);
            List<ExamList> examList = examListDAO.searchByStudent(student);
            for (ExamList el : examList) {
                if (el.getTestId() == testId) {
                    if (hasStarted(testId)) {
                        if (!isTimeUp(testId))
                            return true;
                        else throw new ServiceException("The test has already ended!");
                    } else throw new ServiceException("The test has not yet started!");
                }
            }
            TransactionUtil.commit();

            return false;


        } catch (Exception e) {
            e.printStackTrace();
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());

        } finally {
            TransactionUtil.closeConn();

        }
    }

    private static boolean hasStarted(int testId) {
        ExamDAO examDAO = new ExamDAOImpl();
        Exam ex = examDAO.searchByID(testId);
        long startTime = ex.getStartTime().getTime();

        long now = System.currentTimeMillis();
        if (now >= startTime) {
            return true;
        }
        return false;
    }

    private static boolean isTimeUp(int testId) {
        ExamDAO examDAO = new ExamDAOImpl();
        Exam ex = examDAO.searchByID(testId);
        Timestamp startTime = ex.getStartTime();
        long endTime = startTime.getTime() + ex.getTestDuration().longValue();
        long now = System.currentTimeMillis();
        if (now > endTime) {
            return true;
        }
        return false;
    }

}
