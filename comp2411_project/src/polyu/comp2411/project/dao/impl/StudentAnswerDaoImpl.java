package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.StudentAnswerDAO;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.StudentAnswer;

import java.sql.Connection;
import java.util.List;

public class StudentAnswerDaoImpl extends BaseDAO implements StudentAnswerDAO {
    public StudentAnswerDaoImpl() {
    }

    public StudentAnswerDaoImpl(final Connection connection) {
        super(connection);
    }

    @Override
    public StudentAnswer searchByKey(Student stu, Exam ex, Question que) {
        return null;
    }

    @Override
    public void addStudentAnswer(StudentAnswer stuAns) {

    }

    @Override
    public void delStudentAnswer(StudentAnswer stuAns) {

    }

    @Override
    public void updStudentAnswer(StudentAnswer oldStuAns, StudentAnswer newStudentAns) {

    }

    @Override
    public List<StudentAnswer> searchByStudent(Student stu) {
        return null;
    }

    @Override
    public List<StudentAnswer> searchByExam(Exam ex) {
        return null;
    }

    @Override
    public List<StudentAnswer> searchByQuestion(Exam ex, Question que) {
        return null;
    }
}
