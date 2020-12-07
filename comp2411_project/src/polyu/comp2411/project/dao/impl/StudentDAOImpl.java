package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl extends BaseDAO implements StudentDAO {
    private Student stu;
    private ResultSet rs;

    private ResultSet result;

    public StudentDAOImpl() {
        super();
    }

    public StudentDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Student searchByID(int id) {
        String sql = "SELECT * FROM STUDENT WHERE STU_ID = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                String name = rs.getString("STU_NAME");
                int classNo = rs.getInt("CLASS_NO");
                Student result = new Student(id, name, classNo);
                this.stu = result; // set the stu field as this.
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            closeStatement();
            closePreparedStatement();
        }
         throw new DAOException("Specified entity not found!");
    }

    @Override
    public void addStudent(Student stu) {
        String sql = "INSERT INTO STUDENT VALUES(?,?)"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
            ps.setInt(1, stu.getId());
            ps.setString(2, stu.getName());
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
    public List<Student> searchByExam(Exam ex) {
        String sql = "SELECT * FROM EXAM_LIST NATURAL JOIN STUDENT WHERE TEST_ID = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, ex.getTestId());
            List<Student> ans = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {//todo bug?
                int id = rs.getInt("STU_ID");
                String name = rs.getString("STU_NAME");
                int classNo = rs.getInt("CLASS_NO");
                ans.add(new Student(id, name, classNo));
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
        // throw new DAOException("Specified entity not found!");
    }

    @Override
    public List<Student> searchByClass(Classe cls) {
        String sql = "SELECT * FROM STUDENT WHERE CLASS_NO=?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, cls.getClassNo());
            rs = ps.executeQuery();
            List<Student> ans = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("STU_ID");
                String name = rs.getString("STU_NAME");
                int classNo = rs.getInt("CLASS_NO");
                ans.add(new Student(id, name, classNo));

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
        // throw new DAOException("Specified entity not found!");
    }

}
