package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.entity.*;
import polyu.comp2411.project.util.TransactionUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

public class ExamDAOImpl extends BaseDAO implements ExamDAO {
    private Exam exam;
    private ResultSet rs;

    public ExamDAOImpl() {
        super();
    }

    public ExamDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Exam searchByID(int id) {
        String sql = "SELECT * FROM EXAM WHERE TEST_ID = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                BigDecimal testDuration = BigDecimal.valueOf(rs.getInt("TEST_DURATION"));
                Timestamp startTime = rs.getTimestamp("START_TIME");
                int classNo = rs.getInt("CLASS_NO");
                int subjectId = rs.getInt("SUBJECT_ID");
                int arrangerId = rs.getInt("ARRANGER_ID");
                Exam result = new Exam(id, testDuration, startTime, classNo, subjectId, arrangerId);
                this.exam = result; // set the stu field as this.
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            closeStatement();
            closePreparedStatement();
        }
         return null;
    }

    @Override
    public void addExam(Exam ex) {
        String sql = "INSERT INTO EXAM VALUES(?,?,?,?,?,?)"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
            ps.setInt(1, ex.getTestId());
            ps.setBigDecimal(2, ex.getTestDuration()); // I am not sure for this line...
            ps.setTimestamp(3, ex.getStartTime());
            ps.setInt(4, ex.getClassNo());
            ps.setInt(5, ex.getSubjectId());
            ps.setInt(6, ex.getArrangerId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Integrity constrint violated");
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public void delExam(Exam ex) {
        String sql = "DELETE FROM EXAM WHERE TEST_ID = ?"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
            ps.setInt(1, ex.getTestId());
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
    public void updExam(Exam oldEx, Exam newEx) {
        String sql = "UPDATE EXAM SET TEST_ID=?,TEST_DURATION=?,START_TIME=?,CLASS_NO=?,SUBJECT_ID=?,ARRANGER_ID=? WHERE TEST_ID = ?";

        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
            ps.setInt(1, newEx.getTestId());
            ps.setObject(2, newEx.getTestDuration());
            ps.setTimestamp(3, newEx.getStartTime());
            ps.setInt(4, newEx.getClassNo());
            ps.setInt(5, newEx.getSubjectId());
            ps.setInt(6, newEx.getArrangerId());
            ps.setInt(7, oldEx.getTestId());
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
    public List<Exam> searchBySubject(Subject sub) {
        String sql = "SELECT * FROM EXAM WHERE SUBJECT_ID = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, sub.getId());
            rs = ps.executeQuery();
            List<Exam> ans = new ArrayList<>();
            while (rs.next()) {
                int testId = rs.getInt("TEST_ID");
                BigDecimal testDuration = BigDecimal.valueOf(rs.getInt("TEST_DURATION"));
                Timestamp startTime = rs.getTimestamp("START_TIME");
                int classNo = rs.getInt("CLASS_NO");
                int subjectId = rs.getInt("SUBJECT_ID");
                int arrangerId = rs.getInt("ARRANGER_ID");
                Exam result = new Exam(testId, testDuration, startTime, classNo, subjectId, arrangerId);
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
    public List<Exam> searchBySubAndClass(Subject sub, Classe classe) {
        String sql = "SELECT * FROM EXAM WHERE SUBJECT_ID = ? AND CLASS_NO = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, sub.getId());
            ps.setInt(2, classe.getClassNo());
            rs = ps.executeQuery();
            List<Exam> ans = new ArrayList<>();
            while (rs.next()) {
                int testId = rs.getInt("TEST_ID");
                BigDecimal testDuration = BigDecimal.valueOf(rs.getInt("TEST_DURATION"));
                Timestamp startTime = rs.getTimestamp("START_TIME");
                int classNo = rs.getInt("CLASS_NO");
                int subjectId = rs.getInt("SUBJECT_ID");
                int arrangerId = rs.getInt("ARRANGER_ID");
                Exam result = new Exam(testId, testDuration, startTime, classNo, subjectId, arrangerId);
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
    public int getNextExamId() {
        String sql = "SELECT MAX(TEST_ID) FROM EXAM";
        try {
            // todo testing
            PreparedStatement ps = getConn().prepareStatement(sql);
            // PreparedStatement ps = getConn().prepareStatement(sql);
            // rs = ps.executeQuery();
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt("MAX(TEST_ID)") + 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();

            // rs.close();
        }
        return -1;
    }

    public static void main(String[] args) {
        try {
            Connection connection = TransactionUtil.getConn();
            ExamDAO examDAO = new ExamDAOImpl(connection);
            ExamDAO examDAO1 = new ExamDAOImpl(connection);
            System.out.println(examDAO.getNextExamId());
            System.out.println(examDAO1.getNextExamId());
            // ResultSet rs = examDAO.ps.executeQuery();
            //
            // while (rs.next()){
            // System.out.println(rs.getString("TEACHER_NAME"));
            // }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
    }

}
