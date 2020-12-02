package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Subject;
import polyu.comp2411.project.entity.Teacher;

import java.math.BigInteger;
import java.sql.Timestamp;

public interface TestDesignerService {
    /**
     * create test has following to steps: create a new Test entity and write it to DB.
     * for all student in that class, add this exam to their exam list. This method only
     * create a empty exam with no questions.
     * @param arranger
     * @param cls
     * @param sub
     * @param testDuration
     * @param startTime
     * @return id of the test
     */
    int createTest(Teacher arranger, Classe cls, Subject sub, BigInteger testDuration, Timestamp startTime);
    /**
     * create a question and add it to an exam.
     * @param ex
     * @param qDesc: description of question
     * @param answer: correct answer
     * @param type: type of exam, should be 'MC' or 'FL' or 'FB'
     * @return the No. of newly added question
     */
    int createQuestion(Exam ex, String qDesc, boolean isCompulsory, String type, String answer, int score);
}
