package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.dao.StudentAnswerDAO;
import polyu.comp2411.project.dao.impl.QuestionDAOImpl;
import polyu.comp2411.project.dao.impl.StudentAnswerDaoImpl;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.StudentAnswer;
import polyu.comp2411.project.service.ManualJudgeService;
import polyu.comp2411.project.util.TransactionUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * provide service for teacher to manually judge full-length question
 */
public class ManualJudgeServiceImpl implements ManualJudgeService {


    /**
     * manual judge a particular exam for all student participated in it
     * @param ex the exam
     */
    @Override
    public void judgeAnExam(Exam ex){//todo maybe add condition that only who arrange this test can manual judge this?
        if (ex == null) {
            throw new IllegalArgumentException();
        }
        try {
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);

            //search for all the full-length quesion in this exam
            List<Integer> qNoOfFullLen = new ArrayList<>(); //list of all the full-length question in this exam
            QuestionDAO questionDAO = new QuestionDAOImpl(conn);
            for (Question q : questionDAO.searchByExam(ex)){
                if (q.getType().equals("FL")){ // if this quesion is a full-length question
                    qNoOfFullLen.add(q.getqNo());
                }
            }

            List<StudentAnswer> studentAnswers = studentAnswerDAO.searchByExam(ex);
            for (StudentAnswer sa : studentAnswers) { // for all the answers in this exam
                if (qNoOfFullLen.contains(sa.getQueNo())){ // if this is an answer to a full-length question.
                    judgeAQuestion(sa); // todo: still wandering how this could be interactive.
                }
            }

            TransactionUtil.commit();
        }catch (Exception e){
            e.printStackTrace();
            TransactionUtil.rollBack();
        }finally {
            TransactionUtil.closeConn();
        }

    }

    private void judgeAQuestion(StudentAnswer stuAns){
//        if (stuAns == null) {
//            throw new IllegalStateException();
//        }
//        try {
//            Connection conn =TransactionUtil.getConn();
//            TransactionUtil.startTransaction();
//            QuestionDAO questionDAO = new QuestionDAOImpl(conn);
//            Question q = questionDAO.searchByKey(stuAns.getTestId(), stuAns.getQueNo());
//            //next compare the answer and set score
//            StudentAnswer updatedStuAns = (StudentAnswer) stuAns.clone();
//            if (stuAns.getAnswer() == null || !stuAns.getAnswer().equals(q.getAns())) {//did not answer or wrong answer
//                updatedStuAns.setScore(0);
//            } else {
//                updatedStuAns.setScore(q.getScore()); // get full marks
//            }
//            // next update the StudenAnswer table
//            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);
//            studentAnswerDAO.updStudentAnswer(stuAns,updatedStuAns);
//            TransactionUtil.commit();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            TransactionUtil.rollBack();
//        }finally {
//            TransactionUtil.closeConn();
//        }
    }

}
