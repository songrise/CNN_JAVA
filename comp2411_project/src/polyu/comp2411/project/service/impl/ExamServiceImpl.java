package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.service.ExamService;

/**
 * provide service for student to have online test
 */
public class ExamServiceImpl implements ExamService {


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
     * @param student
     * @param ex
     */
    @Override
    public void sitExam(Student student, Exam ex){
        //todo
    }


    /**
     * judge wheather a student can enter an exam.
     * A student can enter if he has that exam in his exam list
     * and current time is examination period.
     * @param stu
     * @param ex
     * @return
     */
    private boolean canEnterExam(Student stu, Exam ex){
        //todo
        return false;
    }

    /**
     * Answer one quesion.
     * this is tobe called by public void sitExam(Student student, Exam ex)
     *
     * @param stu
     * @param ex
     */
    private void answerQuestion(Question q, Student stu){
        //todo
    }

    /**
     *  insert all the question of this exam
     *  into the StudentTable table for this student, and the
     *  answer is null(which means not answered at the very
     *  begining of exam)
     * @param ex
     * @param stu
     */
    private void initStudentAnswer(Exam ex, Student stu){
        //todo
    }

    private boolean timeUp(Exam ex){
        //todo
        return false;
    }
}
