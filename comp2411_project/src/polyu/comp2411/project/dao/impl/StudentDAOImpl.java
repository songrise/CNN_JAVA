package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.entity.*;

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
    public StudentDAOImpl(){
        super();
    }

    public StudentDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Student searchByID(int id) {
        String sql = "SELECT * FROM STUDENT WHERE STU_ID = ?";
        try{
            setPs(sql);
            getPs().setInt(1,id);
            rs = getPs().executeQuery();
            while(rs.next()){

                String name = rs.getString("STU_NAME");
                int classNo=rs.getInt("CLASS_NO");
                Student result = new Student(id,name,classNo);
                this.stu = result; //set the stu field as this.
                return result;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }

    @Override
    public void addStudent(Student stu) {
        String sql = "INSERT INTO STUDENT VALUES(?,?)"; // parameter to be set later
        try {
            setPs(sql);
            //set parameter of sql
            getPs().setInt(1,stu.getId());
            getPs().setString(2,stu.getName());
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
    public List<Student> searchByExam(Exam ex) {
        String sql = "SELECT * FROM EXAM_LIST NATUAL JOIN STUDENT WHERE TEST_ID = ?";
        try{
            setPs(sql);
            getPs().setInt(1,ex.getTestId());
            List<Student> ans = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("STU_ID");
                String name = rs.getString("STU_NAME");
                int classNo=rs.getInt("CLASS_NO");
                ans.add(new Student(id,name,classNo));
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
    public List<Student> searchByClass(Classe cls) {
        String sql = "SELECT * FROM STUDENT WHERE CLASS_NO = ?";
        try{
            setPs(sql);
            getPs().setInt(1,cls.getClassNo());
            rs = getPs().executeQuery(sql);
            List<Student> ans = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("STU_ID");
                String name = rs.getString("STU_NAME");
                int classNo=rs.getInt("CLASS_NO");
                ans.add(new Student(id,name,classNo));

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


}
