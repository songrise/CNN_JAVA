package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;

import java.util.List;

public interface QuestionDAO {
    Question searchByID(int id);

    void delAllQuestionOfExam(Exam ex);
    void addQuestion(Question que);
    void delQuestion(Question que);
    List<Question> searchByExam(Exam ex);
    //get the next question number of an exam
    int getNextQuestionNo(Exam ex);
}
