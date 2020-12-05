package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.Exam;

public interface TestDesignerService {
    /**
     * create test has following to steps: create a new Test entity and write it to DB.
     * for all student in that class, add this exam to their exam list. This method only
     * create a empty exam with no questions.
     * @param arrangerID
     * @param clsID
     * @param subID
     * @param testDuration
     * @param startTime
     * @return id of the test
     */
    int createTest(int arrangerID, int clsID, int subID, int testDuration, String startTime);
    /**
     * create a question and add it to an exam.
     * @param ex
     * @param qDesc: description of question
     * @param answer: correct answer
     * @param type: type of exam, should be 'MC' or 'FL' or 'FB'
     * @return the No. of newly added question
     */
    int createQuestion(int examID, String qDesc, boolean isCompulsory, String type, String answer, int score);
}
