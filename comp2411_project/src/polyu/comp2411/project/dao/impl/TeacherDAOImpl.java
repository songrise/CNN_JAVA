package polyu.comp2411.project.dao.impl;


import polyu.comp2411.project.dao.TeacherDAO;
import polyu.comp2411.project.dao.impl.BaseDAO;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl extends BaseDAO implements TeacherDAO {
    private Teacher teacher;
    private ResultSet rs;

    private ResultSet result;
    TeacherDAOImpl(){
        super();
    }

    public TeacherDAOImpl(final Connection connection ) {
        super(connection);
    }

    @Override
    public Teacher serchByID(int id) {
        String sql = "SELECT * FROM TEACHER WHERE TEACHER_ID = ?";
        try{
            setPs(sql);
            getPs().setInt(1,id);
            rs = getPs().executeQuery();
            while(rs.next()){

                String name = rs.getString("TEACHER_NAME");
                Teacher result = new Teacher(id,name);
                this.teacher= result; //set the teacher field as this.
                return result;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closeStatement();
            closePreparedStatement();
        }return null;
    }

    @Override
    public void addTeacher(Teacher tc) {
        String sql = "INSERT INTO TEACHER VALUES(?,?)"; // parameter to be set later
        try {
            setPs(sql);
            //set parameter of sql
            getPs().setInt(1,tc.getId());
            getPs().setString(2,tc.getName());
            getPs().execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public List<Teacher> searchByClass(Classe cls) {
        String sql = "SELECT CLASS_NO,DISTINCT TEACHER_ID,TEACHER_NAME " +
                "FROM TEACHER,TEACH_IN " +
                "WHERE CLASS_NO = ? and TEACHER.TEACHER_ID=TEACH_IN.TEACHER_ID";
        try{
            setPs(sql);
            getPs().setInt(1,cls.getClassNo());
            rs = getPs().executeQuery(sql);
            List<Teacher> ans = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("TEACHER_ID");
                String name = rs.getString("TEACHER_NAME");
                ans.add(new Teacher(id,name));
            }
            return ans;
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }

    @Override
    public Teacher searchByClassTeacherOf(Classe cls) {

        String sql = "SELECT CLASS_NO,DISTINCT CLASS_TEACHER_ID,TEACHER_NAME " +
                "FROM TEACHER,CLASS" +
                "WHERE CLASS_NO = ? and TEACHER_ID=CLASS_TEACHER_ID";
        try{
            setPs(sql);
            getPs().setInt(1,cls.getClassNo());
            rs = getPs().executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("TEACHER_ID");
                String name = rs.getString("TEACHER_NAME");
                Teacher ans=new Teacher(id,name);
                this.teacher=ans;
                return ans;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }
}
