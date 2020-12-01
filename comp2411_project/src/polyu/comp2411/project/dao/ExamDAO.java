package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Subject;

import java.sql.*;
import java.util.List;

public interface ExamDAO {

    Exam searchByID(int id);
    void addExam(Exam ex);
    void delExam(Exam ex);
    void updExam(Exam oldEx, Exam newEx);
    List<Exam> searchBySubject(Subject sub);
    List<Exam> searchByClass(Classe cls);
    //get the next question number of an exam
    int getNextExamId();
}
