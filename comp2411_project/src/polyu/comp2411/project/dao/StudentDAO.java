package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Student;

import java.util.List;

public interface StudentDAO {

    Student searchByID(int id);

    void addStudent(Student stu);

    List<Student> searchByClass(Classe cls);

    List<Student> searchByExam(Exam ex);

}
