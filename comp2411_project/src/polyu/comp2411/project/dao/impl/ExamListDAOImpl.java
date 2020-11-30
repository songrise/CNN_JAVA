package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.ExamListDAO;
import polyu.comp2411.project.entity.ExamList;
import polyu.comp2411.project.entity.Student;

import java.util.List;

public class ExamListDAOImpl extends BaseDAO implements ExamListDAO {
    @Override
    public List<ExamList> searchByStudent(Student stu) {
        return null;
    }
}
