package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Subject;

import java.util.List;

public class ExamDAOImpl extends BaseDAO implements ExamDAO {
    @Override
    public Exam searchByID(int id) {
        return null;
    }

    @Override
    public void addExam(Exam ex) {

    }

    @Override
    public List<Exam> searchBySubject(Subject sub) {
        return null;
    }

    @Override
    public List<Exam> searchByClass(Subject Classe) {
        return null;
    }
}
