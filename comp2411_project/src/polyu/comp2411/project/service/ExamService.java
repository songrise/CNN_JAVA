package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Student;

public interface ExamService {
    /**
     * sit in an exam with steps.
     * 1) check if student can enter that exam
     *
     * 2) if can, then insert all the question of this exam
     * into the StudentTable table for this student, and the
     * answer is null(which means not answered at the very
     * begining of exam)
     *
     * 3) pull all the quesions of this exam form Question table
     *
     * 4) let student answer the questions. to answer a question
     * is to update the answer field in StudentAnswer DB with
     * corresponding stuId.
     *
     * 5) terminate the exam if time up, or student anwered all quesions
     * and confirm submit.
     *
     * @param student the student who sit this exam
     * @param ex
     */
    void sitExam(Student student, Exam ex);
}
