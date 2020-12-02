package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.Exam;

public interface AutoJudgeService {
    /**
     * auto judge a particular exam, all student participated in this exam
     * will be judged.
     * @param ex the exam
     */
    void judgeAnExam(Exam ex);
}
