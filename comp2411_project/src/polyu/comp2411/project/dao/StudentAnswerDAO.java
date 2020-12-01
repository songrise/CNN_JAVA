package polyu.comp2411.project.dao;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.StudentAnswer;

import java.util.List;

public interface StudentAnswerDAO {
    //search by studentId examId, questionNo, which together is primary key of studentAnswer
    StudentAnswer searchByKey(Student stu, Exam ex, Question que);
    void addStudentAnswer(StudentAnswer stuAns);
    void delStudentAnswer(StudentAnswer stuAns);
    void updStudentAnswer(StudentAnswer oldStuAns, StudentAnswer newStudentAns);
    List<StudentAnswer> searchByStudent(Student stu);
    List<StudentAnswer> searchByExam(Exam ex);
    // primary key of quesion is testID + quesion No.
    List<StudentAnswer> searchByQuestion(Exam ex, Question que);
}
