package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.Subject;
import polyu.comp2411.project.entity.Teacher;

import java.util.List;

public interface SubjectDAO {
    Subject searchByID(int id);
    void addSubject(Subject sub);

    List<Subject> searchByTeacher(Teacher tc);
    List<Subject> searchByClass(Classe cls);
    List<Subject> searchByStudent(Student stu);
}
