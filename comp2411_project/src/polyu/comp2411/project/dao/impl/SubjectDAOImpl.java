package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.SubjectDAO;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.Subject;
import polyu.comp2411.project.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl extends BaseDAO implements SubjectDAO {
    private Subject subject;
    private ResultSet rs;

    private ResultSet result;

    public SubjectDAOImpl() {
        super();
    }

    public SubjectDAOImpl(final Connection connection) {
        super(connection);
    }

    @Override
    public Subject searchByID(int id) {
        String sql = "SELECT * FROM SUBJECT WHERE SUBJECT_ID = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("SUBJECT_NAME");
                Subject result = new Subject(id, name);
                this.subject = result; // set the teacher field as this.
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
    public void addSubject(Subject sub) {
        String sql = "INSERT INTO SUBJECT VALUES(?,?)"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
            ps.setInt(1, sub.getId());
            ps.setString(2, sub.getName());
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
    public List<Subject> searchByClass(Classe cls) {
        String sql = "SELECT DISTINCT SUBJECT.SUBJECT_ID,SUBJECT_NAME " + "FROM SUBJECT,OFFERED_IN "
                + "WHERE CLASS_NO = ? AND SUBJECT.SUBJECT_ID=OFFERED_IN.SUBJECT_ID";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, cls.getClassNo());
            rs = ps.executeQuery();
            List<Subject> ans = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("SUBJECT_ID");
                String name = rs.getString("SUBJECT_NAME");
                ans.add(new Subject(id, name));
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

    }

    @Override
    public List<Subject> searchByTeacher(Teacher tc) {
        String sql = "SELECT DISTINCT SUBJECT_ID,SUBJECT_NAME " + "FROM SUBJECT,TEACH_FOR "
                + "WHERE TEACHER_ID = ? and SUBJECT.SUBJECT_ID=TEACH_FOR.SUBJECT_ID";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, tc.getId());
            rs = ps.executeQuery();
            List<Subject> ans = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("SUBJECT_ID");
                String name = rs.getString("SUBJECT_NAME");
                ans.add(new Subject(id, name));
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
    public List<Subject> searchByStudent(Student stu) {
        String sql = "SELECT DISTINCT SUBJECT_ID,SUBJECT_NAME " + "FROM SUBJECT,OFFERED_IN "
                + "WHERE CLASS_NO = ? and SUBJECT.SUBJECT_ID=OFFERED_IN.SUBJECT_ID";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, stu.getClassNo());
            rs = ps.executeQuery();
            List<Subject> ans = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("SUBJECT_ID");
                String name = rs.getString("SUBJECT_NAME");
                ans.add(new Subject(id, name));
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
