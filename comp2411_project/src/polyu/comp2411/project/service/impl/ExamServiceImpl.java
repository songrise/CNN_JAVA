package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.dao.StudentAnswerDAO;
import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.dao.impl.QuestionDAOImpl;
import polyu.comp2411.project.dao.impl.StudentAnswerDaoImpl;
import polyu.comp2411.project.dao.impl.StudentDAOImpl;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.StudentAnswer;
import polyu.comp2411.project.service.ExamService;
import polyu.comp2411.project.util.TransactionUtil;

import java.sql.Connection;
import java.util.List;

/**
 * provide service for student to have online test
 */
public class ExamServiceImpl implements ExamService {


    /**
     * sit in an exam with steps.
     * 1) check if student can enter that exam
     *
     * 2) if can, then insert all the question of this exam
     * into the StudentAnswer table for this student, and the
     * answer is null(which means not answered at the very
     * begining of exam)
     *
     *  @param stuId
     * @param testId
     * @return
     */
    @Override
    public List<Question> sitExam(int stuId, int testId){

        try{
            if(!canEnterExam(stuId, testId)){
                throw new IllegalAccessError("Student " + stuId +" has no access to "+ testId +" now!");
            }
            //else that student can
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);
            QuestionDAO questionDAO = new QuestionDAOImpl(conn);
            ExamDAO examDAO = new ExamDAOImpl(conn);

            List<Question> questionsInThisExam = questionDAO.searchByExam(examDAO.searchByID(testId));//all questions in this exam
            for (Question q : questionsInThisExam){
                //create an empty record for each question, and append that to student answer
                StudentAnswer emptyAnswer = new StudentAnswer(stuId, testId,q.getqNo(),null,0);
                studentAnswerDAO.addStudentAnswer(emptyAnswer);
            }
            TransactionUtil.commit();
            //return the question list for student;

            return questionsInThisExam;
        }catch (Exception e){
            e.printStackTrace();
            TransactionUtil.rollBack();
        }finally {
            TransactionUtil.closeConn();
        }
        return null;
    }



    @Override
    public void answerAnQuestion(Question que, int stuId, String stuAnsStr) {
        if (que == null||stuAnsStr==null) {
            throw new IllegalArgumentException();
        }
        try{
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);
            StudentDAO studentDAO = new StudentDAOImpl(conn);
            ExamDAO examDAO = new ExamDAOImpl(conn);
            Student student = studentDAO.searchByID(stuId);
            Exam exam = examDAO.searchByID(que.getTestId());

            StudentAnswer studentAnswer = studentAnswerDAO.searchByKey(student,exam,que);
            StudentAnswer newAnswer = (StudentAnswer) studentAnswer.clone();
            newAnswer.setAnswer(stuAnsStr);
            studentAnswerDAO.updStudentAnswer(studentAnswer,newAnswer);

            TransactionUtil.commit();
        }catch (Exception e){
            e.printStackTrace();
            TransactionUtil.rollBack();
        }finally {
            TransactionUtil.closeConn();
        }
    }


    /**
     * judge wheather a student can enter an exam.
     * A student can enter if he has that exam in his exam list
     * and current time is examination period.
     * @param stu
     * @param ex
     * @return
     */
    private boolean canEnterExam(int stuId, int testId){
        //todo
        return true;
    }


    /**
     *  insert all the question of this exam
     *  into the StudentTable table for this student, and the
     *  answer is null(which means not answered at the very
     *  begining of exam)
     * @param ex
     * @param stu
     */
    private void initStudentAnswer(Exam ex, Student stu){
        //todo
    }

    private boolean timeUp(Exam ex){
        //todo
        return false;
    }
}
