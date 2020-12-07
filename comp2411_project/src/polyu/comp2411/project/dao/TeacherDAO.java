package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Teacher;

import java.util.List;

public interface TeacherDAO {
    Teacher serchByID(int id);

    void addTeacher(Teacher tc);

    //this is search subject teacher
    List<Teacher> searchByClass(Classe cls);

    // this is search class teacher
    Teacher searchByClassTeacherOf(Classe cls);
}
