package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.ScoreList;
import polyu.comp2411.project.entity.Student;

import java.util.List;

public interface ScoreListDAO {
    //search by studentId and examId, which together is primary key of scoreList
    ScoreList searchByKey(Student stu, Exam ex);
    void addScoreList(ScoreList sl);
    void delScoreList(ScoreList sl);
    void updScoreList(ScoreList oldSl,ScoreList newSl);
    List<ScoreList> searchByStudent(Student stu);
    List<ScoreList> searchByExam(Exam ex);
}
