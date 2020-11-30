package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.ScoreList;
import polyu.comp2411.project.entity.Student;

import java.util.List;

public interface ScoreListDAO {
    void addScoreList(ScoreList sl);
    void delScoreList(ScoreList sl);
    List<ScoreList> searchByStudent(Student stu);
}
