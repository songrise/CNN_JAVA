package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.StudentAnswerDAO;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.StudentAnswer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentAnswerDaoImpl extends BaseDAO implements StudentAnswerDAO {
    public StudentAnswerDaoImpl() {
    }

    public StudentAnswerDaoImpl(final Connection connection) {
        super(connection);
    }

    @Override
    public StudentAnswer searchByKey(Student stu, Exam ex, Question que) {
        String sql = "SELECT * FROM STUDENT_ANSWER WHERE STU_ID = ? AND TEST_ID = ? AND QUESTION_NO = ?"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
            ps.setInt(1, stu.getId());
            ps.setInt(2, ex.getTestId());
            ps.setInt(3, que.getqNo());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int stuId = rs.getInt("STU_ID");
                int testId = rs.getInt("TEST_ID");
                int questionNo = rs.getInt("QUESTION_NO");
                String stuAns = rs.getString("ANSWER");
                int score = rs.getInt("SCORE");
                return new StudentAnswer(stuId, testId, questionNo, stuAns, score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }

    @Override
    public void addStudentAnswer(StudentAnswer stuAns) {
        String sql = "INSERT INTO STUDENT_ANSWER VALUES(?,?,?,?,?)"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
            ps.setInt(1, stuAns.getStuId());
            ps.setInt(2, stuAns.getTestId());
            ps.setInt(3, stuAns.getQueNo());
            ps.setString(4, stuAns.getAnswer());
            ps.setInt(5, 0);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public void delStudentAnswer(StudentAnswer stuAns) {

    }

    @Override
    public void updStudentAnswer(StudentAnswer oldStuAns, StudentAnswer newStudentAns) {
        String sql = "UPDATE STUDENT_ANSWER SET ANSWER = ?, SCORE = ? WHERE STU_ID = ? AND TEST_ID = ? AND QUESTION_NO = ?"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
            ps.setString(1, newStudentAns.getAnswer());
            ps.setInt(2, newStudentAns.getScore());
            ps.setInt(3, oldStuAns.getStuId());
            ps.setInt(4, oldStuAns.getTestId());
            ps.setInt(5, oldStuAns.getQueNo());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public List<StudentAnswer> searchByStudent(Student stu) {
        String sql = "SELECT * FROM Question WHERE STU_ID=?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, stu.getId());
            ResultSet rs = ps.executeQuery();
            List<StudentAnswer> ans = new ArrayList<>();
            while (rs.next()) {
                int stuId = rs.getInt("STU_ID");
                int testId = rs.getInt("TEST_ID");
                int questionNo = rs.getInt("QUESTION_NO");
                String stuAns = rs.getString("ANSWER");
                int score = rs.getInt("SCORE");
                StudentAnswer result = new StudentAnswer(stuId, testId, questionNo, stuAns, score);
                ans.add(result);
            }
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
        // return null;
    }

    @Override
    public List<StudentAnswer> searchByExam(Exam ex) {
        String sql = "SELECT * FROM STUDENT_ANSWER WHERE TEST_ID=?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, ex.getTestId());
            ResultSet rs = ps.executeQuery();
            List<StudentAnswer> ans = new ArrayList<>();
            while (rs.next()) {
                int stuId = rs.getInt("STU_ID");
                int testId = rs.getInt("TEST_ID");
                int questionNo = rs.getInt("QUESTION_NO");
                String stuAns = rs.getString("ANSWER");
                int score = rs.getInt("SCORE");
                StudentAnswer result = new StudentAnswer(stuId, testId, questionNo, stuAns, score);
                ans.add(result);
            }
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
        // return null;
    }

    @Override
    public List<StudentAnswer> searchByQuestion(Question que) {
        String sql = "SELECT * FROM STUDENT_ANSWER WHERE TEST_ID=? AND QUESTION_NO = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, que.getTestId());
            ps.setInt(2, que.getqNo());
            ResultSet rs = ps.executeQuery();
            List<StudentAnswer> ans = new ArrayList<>();
            while (rs.next()) {
                int stuId = rs.getInt("STU_ID");
                int testId = rs.getInt("TEST_ID");
                int questionNo = rs.getInt("QUESTION_NO");
                String stuAns = rs.getString("ANSWER");
                int score = rs.getInt("SCORE");
                StudentAnswer result = new StudentAnswer(stuId, testId, questionNo, stuAns, score);
                ans.add(result);
            }
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
        // return null;
    }
}
