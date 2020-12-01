package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;

import java.util.List;

public interface QuestionDAO {
    Question searchByKey(int testId, int qNo);

    void delAllQuestionOfExam(Exam ex);
    void addQuestion(Question que);
    void delQuestion(Question que);
    void updQuesiton(Question oldQue, Question newQue);
    List<Question> searchByExam(Exam ex);
    //get the next question number of an exam
    int getNextQuestionNo(Exam ex);
}
