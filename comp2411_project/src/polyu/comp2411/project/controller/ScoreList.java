package polyu.comp2411.project.controller;

import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.dao.impl.ScoreListDAOImpl;
import polyu.comp2411.project.dao.ScoreListDAO;


/**
 * students use this to view his score(letter grade) of all the exams
 */
public class ShowScoreList {
    public void showScoreList(Student student){
        ScoreListDAO scoreList=new ScoreListDAOImpl();
        System.out.println(scoreList.searchByStudent(student));
    }
}
