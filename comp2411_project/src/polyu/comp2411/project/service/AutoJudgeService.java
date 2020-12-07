package polyu.comp2411.project.service;

public interface AutoJudgeService {
    /**
     * auto judge a particular exam, all student participated in this exam
     * will be judged.
     *
     * @param testId the exam
     */
    void judgeAnExam(int testId);
}
