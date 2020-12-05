package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.ClasseDAO;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class ClasseDAOImpl extends BaseDAO implements ClasseDAO {
    private Classe classe;
    private ResultSet rs;

    private ResultSet result;

    ClasseDAOImpl() {
        super();
    }

    public ClasseDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Classe searchById(int id) {
        String sql = "SELECT * FROM CLASS WHERE CLASS_NO = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int classNo = rs.getInt("CLASS_NO");
                int classTeacherId = rs.getInt("CLASS_TEACHER_ID");
                Classe result = new Classe(classNo, classTeacherId);
                this.classe = result; // set the stu field as this.
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }

    @Override
    public Classe searchByClassTeacher(Teacher tc) {
        String sql = "SELECT * FROM CLASS WHERE CLASS_TEACHER_ID = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, tc.getId());
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                int classNo = rs.getInt("CLASS_NO");
                int classTeacherId = rs.getInt("CLASS_TEACHER_ID");
                Classe ans = new Classe(classNo, classTeacherId);
                this.classe = ans;
                return ans;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }
}
