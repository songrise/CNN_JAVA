package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.StudentAnswer;

import java.util.List;

public interface ManualJudgeService {
    /**
     * fetch all full-length question of an exam for manual judge.
     * @param ex the exam
     * @return a list of full length quesion in this exam.
     */
    List<StudentAnswer> fetchFLofAnExam(Exam ex);

    void manualJudgeAQuesion(StudentAnswer stuAns, int score);
}
