package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.Question;

import java.util.List;

public interface ExamService {
    /**
     * sit in an exam with steps.
     * 1) check if student can enter that exam
     * <p>
     * 2) if can, then insert all the question of this exam
     * into the StudentAnswer table for this student, and the
     * answer is null(which means not answered at the very
     * begining of exam)
     *
     * @param stuId  the student who sit this exam
     * @param testId
     * @return all the questions in this exam
     */
    List<Question> sitExam(int stuId, int testId);

    /**
     * controller call this iterativly to answer questions
     *
     * @param que
     * @param stuId
     * @param stuAnswerStr the answer that student made.
     */
    void answerAnQuestion(Question que, int stuId, String stuAnswerStr);
}
