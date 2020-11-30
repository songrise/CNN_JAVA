package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.Subject;

import java.sql.*;
import java.util.List;

public interface ExamDAO {

    Exam searchByID(int id);
    void addExam(Exam ex);
    void delExam(Exam ex);
    List<Exam> searchBySubject(Subject sub);
    List<Exam> searchByClass(Subject Classe);
}
