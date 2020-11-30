package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.SubjectDAO;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.Subject;
import polyu.comp2411.project.entity.Teacher;

import java.util.List;

public class SubjectDAOImpl extends BaseDAO implements SubjectDAO {
    @Override
    public Subject searchByID(int id) {
        return null;
    }

    @Override
    public void addSubject(Subject sub) {

    }

    @Override
    public List<Subject> searchByClass(Class cls) {
        return null;
    }

    @Override
    public List<Subject> searchByTeacher(Teacher tc) {
        return null;
    }

    @Override
    public List<Subject> searchByClass(Classe cls) {
        return null;
    }

    @Override
    public List<Subject> searchByStudent(Student stu) {
        return null;
    }
}
