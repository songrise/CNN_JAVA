package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.ExamList;
import polyu.comp2411.project.entity.Student;

import java.util.List;

public interface ExamListDAO {
    void addExamList(ExamList el);

    void delExamList(ExamList el);

    void updExamList(ExamList oldEl, ExamList newEl);

    // An examList is just one record. return value of this is a list of exam
    List<ExamList> searchByStudent(Student stu);


}
