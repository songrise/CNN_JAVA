package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;

import java.util.List;

public interface ExamService {
    /**
     * sit in an exam with steps.
     * 1) check if student can enter that exam
     *
     * 2) if can, then insert all the question of this exam
     * into the StudentAnswer table for this student, and the
     * answer is null(which means not answered at the very
     * begining of exam)
     *
     * @param student the student who sit this exam
     * @param ex
     * @return all the questions in this exam
     */
    List<Question> sitExam(Student student, Exam ex);

    /**
     * controller call this iterativly to answer questions
     * @param que
     * @param stu
     * @param stuAnswerStr the answer that student made.
     */
    void answerAnQuestion(Question que, Student stu, String stuAnswerStr);
}
