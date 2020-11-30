package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ExamDAOImpl extends BaseDAO implements ExamDAO {
    private Exam exam;
    private ResultSet rs;

    private ResultSet result;
    public ExamDAOImpl(){
        super();
    }

    @Override
    public Exam searchByID(int id) {
        String sql = "SELECT * FROM EXAM WHERE TEST_ID = ?";
        try{
            setPs(sql);
            getPs().setInt(1,id);
            rs = getPs().executeQuery();
            while(rs.next()){
                BigInteger testDuration = BigInteger.valueOf(rs.getInt("TEST_DURATION"));
                Timestamp startTime=rs.getTimestamp("START_TIME");
                int classNo = rs.getInt("CLASS_NO");
                int subjectId = rs.getInt("SUBJECT_ID");
                int arrangerId = rs.getInt("ARRANGER_ID");
                Exam result=new Exam(id,testDuration,startTime,classNo,subjectId,arrangerId);
                this.exam = result; //set the stu field as this.
                return result;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closeConn();
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }

    @Override
    public void addExam(Exam ex) {
        String sql = "INSERT INTO EXAM VALUES(?,?,?,?,?,?)"; // parameter to be set later
        try {
            setPs(sql);
            //set parameter of sql
            getPs().setInt(1,ex.getTestId());
            getPs().setObject(2,ex.getTestDuration());  //I am not sure for this line...
            getPs().setTimestamp(3,ex.getStartTime());
            getPs().setInt(4,ex.getClassNo());
            getPs().setInt(5,ex.getSubjectId());
            getPs().setInt(6,ex.getArrangerId());
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
    public void delExam(Exam ex) {

    }

    @Override
    public List<Exam> searchBySubject(Subject sub) {
        String sql = "SELECT * FROM EXAM WHERE SUBJECT_ID = ?";
        try{
            setPs(sql);
            getPs().setInt(1,sub.getId());
            rs = getPs().executeQuery();
            List<Exam> ans = new ArrayList<>();
            while(rs.next()){
                int testId=rs.getInt("TEST_ID");
                BigInteger testDuration = BigInteger.valueOf(rs.getInt("TEST_DURATION"));
                Timestamp startTime=rs.getTimestamp("START_TIME");
                int classNo = rs.getInt("CLASS_NO");
                int subjectId = rs.getInt("SUBJECT_ID");
                int arrangerId = rs.getInt("ARRANGER_ID");
                Exam result=new Exam(testId,testDuration,startTime,classNo,subjectId,arrangerId);
                ans.add(result);
            }
            return ans;
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            // close resources used
            closeConn();
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }

    @Override
    public List<Exam> searchByClass(Classe classe) {
        String sql = "SELECT * FROM EXAM WHERE CLASS_NO = ?";
        try{
            setPs(sql);
            getPs().setInt(1,classe.getClassNo());
            rs = getPs().executeQuery();
            List<Exam> ans = new ArrayList<>();
            while(rs.next()){
                int testId=rs.getInt("TEST_ID");
                BigInteger testDuration = BigInteger.valueOf(rs.getInt("TEST_DURATION"));
                Timestamp startTime=rs.getTimestamp("START_TIME");
                int classNo = rs.getInt("CLASS_NO");
                int subjectId = rs.getInt("SUBJECT_ID");
                int arrangerId = rs.getInt("ARRANGER_ID");
                Exam result=new Exam(testId,testDuration,startTime,classNo,subjectId,arrangerId);
                ans.add(result);
            }
            return ans;
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            // close resources used
            closeConn();
            closeStatement();
            closePreparedStatement();
        }
        return null;
    }

    @Override
    public int getNextExamId() {
        String sql = "SELECT MAX(TEST_ID) FROM EXAM";
        try{
            setPs(sql);

            rs = getPs().executeQuery();
            while(rs.next()){
                return rs.getInt("TEST_ID")+1;
            }
            return 0;

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            // close resources used
            closeConn();
            closeStatement();
            closePreparedStatement();
        }
        return -1;
    }


}
