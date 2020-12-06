package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.dao.StudentAnswerDAO;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.dao.impl.QuestionDAOImpl;
import polyu.comp2411.project.dao.impl.StudentAnswerDaoImpl;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.StudentAnswer;
import polyu.comp2411.project.service.AutoJudgeService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.util.TransactionUtil;

import java.sql.Connection;
import java.util.List;

/**
 * provide service for auto-judge multiple choice, fill-in-blank
 */
public class AutoJudgeServiceImpl implements AutoJudgeService {

    /**
     * auto judge a particular exam, all student participated in this exam will be
     * judged.
     * 
     * @param testId the exam
     */
    @Override
    public void judgeAnExam(int testId) {

        try {
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            ExamDAO examDAO = new ExamDAOImpl(conn);
            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);
            Exam ex = examDAO.searchByID(testId);
            List<StudentAnswer> studentAnswers = studentAnswerDAO.searchByExam(ex);
            for (StudentAnswer sa : studentAnswers) { // for all the answers in this exam
                judgeAQuestion(sa);
            }
            TransactionUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());
        } finally {
            TransactionUtil.closeConn();
        }

    }

    // TODO: buggy, conn should conform with caller
    private void judgeAQuestion(StudentAnswer stuAns) {
        if (stuAns == null) {
            throw new ServiceException();
        }
        try {
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            QuestionDAO questionDAO = new QuestionDAOImpl(conn);
            Question q = questionDAO.searchByKey(stuAns.getTestId(), stuAns.getQueNo());
            // next compare the answer and set score
            StudentAnswer updatedStuAns = (StudentAnswer) stuAns.clone();
            if (stuAns.getAnswer() == null || !stuAns.getAnswer().equals(q.getAns())) {// did not answer or wrong answer
                updatedStuAns.setScore(0);
            } else {
                updatedStuAns.setScore(q.getScore()); // get full marks
            }
            // next update the StudenAnswer table
            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);
            studentAnswerDAO.updStudentAnswer(stuAns, updatedStuAns);
            TransactionUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());
        } finally {
            TransactionUtil.closeConn();
        }
    }

}
