package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.ExamList;
import polyu.comp2411.project.entity.Student;

import java.util.List;

public interface ExamListDAO {
    //An examList is just one record. it is pretty much like a list of exam
    List<ExamList> searchByStudent(Student stu);
}
