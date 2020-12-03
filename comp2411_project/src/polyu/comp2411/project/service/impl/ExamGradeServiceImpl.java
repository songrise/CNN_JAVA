package polyu.comp2411.project.service.impl;


import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.dao.ScoreListDAO;
import polyu.comp2411.project.dao.StudentAnswerDAO;
import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.dao.impl.QuestionDAOImpl;
import polyu.comp2411.project.dao.impl.ScoreListDAOImpl;
import polyu.comp2411.project.dao.impl.StudentAnswerDaoImpl;
import polyu.comp2411.project.dao.impl.StudentDAOImpl;
import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.service.ExamGradeService;
import polyu.comp2411.project.util.TransactionUtil;

import java.sql.Connection;
import java.util.List;

/**
 * provide service for calculate and update exam result
 */
public class ExamGradeServiceImpl implements ExamGradeService {


    /**
     * calculate total score of exam. This means the score of
     * all the student participated in this exam will be
     * calculated. The score means original points, not
     * letter grade.
     *
     * The score a student get should be porpotional to the
     * full mark. (i.e. normalized to x/100 points)
     *
     * When this method are called, all question shall already
     * be judged.
     * @param ex
     */
    @Override
    public void calScoreOfExam(Exam ex){
        if (ex == null) {
            throw new IllegalArgumentException();
        }
        try{
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);
            StudentDAO studentDAO = new StudentDAOImpl(conn);
            ScoreListDAO scoreListDAO = new ScoreListDAOImpl(conn);

            List<StudentAnswer> allAnswers = studentAnswerDAO.searchByExam(ex);//get all answer of this exam
            List<Student> studentsInThisExam = studentDAO.searchByExam(ex);
            int fullMark = getFullMarkOfExam(ex);

            for (Student stu:studentsInThisExam){//grade all student in this exam
                int score = 0; //score that this student got in this exam
                for(StudentAnswer ans:allAnswers){
                    if(ans.getStuId() == stu.getId()){//if this ans is made by this student.
                        score+=ans.getScore(); // add the score that this student got on this question.
                    }
                }
                int normalizedScore = (score*100/fullMark);
                ScoreList sl = new ScoreList(stu.getId(),ex.getTestId(),normalizedScore,null);
                scoreListDAO.addScoreList(sl);
            }

            TransactionUtil.commit();
        }catch (Exception e){
            e.printStackTrace();
            TransactionUtil.rollBack();
        }finally {
            TransactionUtil.closeConn();
        }
    }

    @Override
    public void addComment(ScoreList sl, String commnet) {

    }

    private int getFullMarkOfExam(Exam ex){
        if (ex == null) {
            throw new IllegalArgumentException();
        }
        try{
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            QuestionDAO questionDAO = new QuestionDAOImpl(conn);

            List<Question> allQuestion = questionDAO.searchByExam(ex);
            int fullMarks = 0;
            for (Question q : allQuestion){
                fullMarks+=q.getScore();
            }
            TransactionUtil.commit();
            return fullMarks;
        }catch (Exception e){
            e.printStackTrace();
            TransactionUtil.rollBack();
        }finally {
            TransactionUtil.closeConn();
        }
        return -1;
    }

}
