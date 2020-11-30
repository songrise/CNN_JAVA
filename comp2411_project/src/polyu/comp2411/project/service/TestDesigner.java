package polyu.comp2411.project.service;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Subject;
import polyu.comp2411.project.entity.Teacher;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * provide service for teacher to Design a exam
 */
public class TestDesigner {


    /**
     * create test has following to steps: create a new Test entity and write it to DB.
     * for all student in that class, add this exam to their exam list
     * @param arranger
     * @param cls
     * @param sub
     * @param testDuration
     * @param startTime
     * @return id of the test
     */
    public int createTest(Teacher arranger, Classe cls, Subject sub, BigInteger testDuration, Timestamp startTime){

    }
}
