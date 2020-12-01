package polyu.comp2411.project.service;

import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.dao.StudentAnswerDAO;
import polyu.comp2411.project.dao.impl.QuestionDAOImpl;
import polyu.comp2411.project.dao.impl.StudentAnswerDaoImpl;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.StudentAnswer;

import java.util.List;

/**
 * provide service for auto-judge multiple choice, fill-in-blank
 */
public class AutoJudgeService {


    /**
     * auto judge a particular exam, all student participated in this exam
     * will be judged.
     * @param ex the exam
     */
    public void judgeAnExam(Exam ex){
        if (ex == null) {
            throw new IllegalArgumentException();
        }
        StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl();
        List<StudentAnswer> studentAnswers = studentAnswerDAO.searchByExam(ex);
        for (StudentAnswer sa : studentAnswers){ // for all the answers in this exam
            judgeAQuestion(sa);
        }

    }

    private void judgeAQuestion(StudentAnswer stuAns){
    try {
        QuestionDAO questionDAO = new QuestionDAOImpl();
        Question q = questionDAO.searchByKey(stuAns.getTestId(), stuAns.getQueNo());
        //next compare the answer and set score
        StudentAnswer updatedStuAns = (StudentAnswer) stuAns.clone();
        if (stuAns.getAnswer() == null || !stuAns.getAnswer().equals(q.getAns())) {//did not answer or wrong answer
            updatedStuAns.setScore(0);
        } else {
            updatedStuAns.setScore(q.getScore()); // get full marks
        }
        // next update the StudenAnswer table
        StudentAnswerDAO studentAnswerDAO = new StudentAnswerDaoImpl();
        studentAnswerDAO.updStudentAnswer(stuAns,updatedStuAns);
    }
    catch (Exception e){
        e.printStackTrace();
    }
    }


}
