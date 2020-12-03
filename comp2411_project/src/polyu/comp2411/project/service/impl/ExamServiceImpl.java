package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.dao.StudentAnswerDAO;
import polyu.comp2411.project.dao.impl.QuestionDAOImpl;
import polyu.comp2411.project.dao.impl.StudentAnswerDaoImpl;
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
     *  @param student
     * @param ex
     * @return
     */
    @Override
    public List<Question> sitExam(Student student, Exam ex){
        if (student == null||ex == null) {
            throw new IllegalArgumentException();
        }
        try{
            if(!canEnterExam(student,ex)){
                throw new IllegalAccessError("Student " +student+"has no access to "+ex+" now!");
            }
            //else that student can
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);
            QuestionDAO questionDAO = new QuestionDAOImpl(conn);

            List<Question> questionsInThisExam = questionDAO.searchByExam(ex);//all questions in this exam
            for (Question q : questionsInThisExam){
                //create an empty record for each question, and append that to student answer
                StudentAnswer emptyAnswer = new StudentAnswer(student.getId(),ex.getTestId(),q.getqNo(),null,0);
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
    public void answerAnQuestion(Question que, Student stu,String stuAnsStr) {
        if (que == null||stu==null||stuAnsStr==null) {
            throw new IllegalArgumentException();
        }
        try{
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl(conn);

            StudentAnswer studentAnswer = new StudentAnswer(stu.getId(),que.getTestId(), que.getqNo(),stuAnsStr,0);
            studentAnswerDAO.addStudentAnswer(studentAnswer);

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
    private boolean canEnterExam(Student stu, Exam ex){
        //todo
        return false;
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
