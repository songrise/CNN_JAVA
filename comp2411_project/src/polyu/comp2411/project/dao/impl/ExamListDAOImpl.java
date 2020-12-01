package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.ExamListDAO;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.ExamList;
import polyu.comp2411.project.entity.Student;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ExamListDAOImpl extends BaseDAO implements ExamListDAO {
    private ExamList examList;
    private ResultSet rs;

    private ResultSet result;

    public ExamListDAOImpl() {
        super();
    }

    @Override
    public void addExamList(ExamList el) {
        String sql = "INSERT INTO EXAM_LIST VALUES(?,?)"; // parameter to be set later
        try {
            setPs(sql);
            // set parameter of sql
            getPs().setInt(1, el.getStuId());
            getPs().setInt(2, el.getTestId());
            getPs().execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close resources used
            closeConn();
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public void delExamList(ExamList el) {
        String sql = "DELETE FROM EXAM_LIST WHERE TEST_ID = ?"; // parameter to be set later
        try {
            setPs(sql);
            // set parameter of sql
            getPs().setInt(1, el.getTestId());
            getPs().execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close resources used
            closeConn();
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public void updExamList(ExamList oldEl, ExamList newEl) {
        String sql = "UPDATE EXAMLIST SET STU_ID=?,TEST_ID=? WHERE TEST_ID = ?"; // parameter to be set later
        try {
            setPs(sql);
            //set parameter of sql
            getPs().setInt(1, newEl.getStuId());
            getPs().setInt(2, newEl.getTestId());
            getPs().setInt(3, oldEl.getTestId());
            getPs().execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            // close resources used
            closeConn();
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public List<ExamList> searchByStudent(Student stu) {
        String sql = "SELECT * FROM EXAM_LIST WHERE STU_ID = ?";
        try {
            setPs(sql);
            getPs().setInt(1, stu.getId());
            rs = getPs().executeQuery();
            List<ExamList> ans = new ArrayList<>();
            while (rs.next()) {
                int stuId = rs.getInt("STU_ID");
                int testId = rs.getInt("TEST_ID");

                ExamList result = new ExamList(stuId, testId);
                ans.add(result);
            }
            return ans;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close resources used
            closeConn();
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }
}
