package polyu.comp2411.project.service;


import polyu.comp2411.project.entity.Exam;

/**
 * provide service for calculate and update exam result
 */
public class ExamResult {


    /**
     * calculate score of exam. This means the score of
     * all the student participated in this exam will be
     * calculated. The score means original points, not
     * letter grade.
     *
     * The score a student get should be porpotional to the
     * full mark. (i.e. normalized to x/100 points)
     *
     * When this method are called, all question shall already
     * be judged.
     * @param ex
     */
    public void calScoreOfExam(Exam ex){

    }
}
