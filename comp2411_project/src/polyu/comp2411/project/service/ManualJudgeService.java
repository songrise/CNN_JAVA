package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.Exam;

public interface ManualJudgeService {
    /**
     * manual judge a particular exam for all student participated in it
     * @param ex the exam
     */
    void judgeAnExam(Exam ex);
}
