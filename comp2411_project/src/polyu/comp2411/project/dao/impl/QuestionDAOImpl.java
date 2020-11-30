package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;

import java.util.List;

public class QuestionDAOImpl extends BaseDAO implements QuestionDAO {
    @Override
    public Question searchByID(int id) {
        return null;
    }

    @Override
    public void addQuestion(Question que) {

    }

    @Override
    public void delQuestion(Question que) {

    }

    @Override
    public List<Question> searchByExam(Exam ex) {
        return null;
    }
}
