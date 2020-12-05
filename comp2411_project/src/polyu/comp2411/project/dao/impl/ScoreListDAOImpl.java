package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.ScoreListDAO;
import polyu.comp2411.project.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreListDAOImpl extends BaseDAO implements ScoreListDAO {
    private ScoreList scoreList;
    private ResultSet rs;

    private ResultSet result;
    public ScoreListDAOImpl(){
        super();
    }
    public ScoreListDAOImpl(Connection connection){
        super(connection);
    }

    @Override
    public ScoreList searchByKey(Student stu, Exam ex) {
        String sql = "SELECT * FROM SCORELIST WHERE STU_ID = ? AND TEST_ID=?";
        try{
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1,stu.getId());
            ps.setInt(2,ex.getTestId());
            rs = ps.executeQuery();
            while(rs.next()){
                int score = rs.getInt("SCORE");
                String feedback = rs.getString("FEEDBACK");
                ScoreList result = new ScoreList(stu.getId(),ex.getTestId(),score,feedback);
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
    public void addScoreList(ScoreList sl) {
        String sql = "INSERT INTO SCORE_LIST VALUES(?,?,?,?)"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            //set parameter of sql
            ps.setInt(1, sl.getStuId());
            ps.setInt(2,sl.getTestId());
            ps.setInt(3,sl.getScore());
            ps.setString(4,sl.getFeedBack());
            ps.execute();
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
    public void delScoreList(ScoreList sl) {
        String sql = "DELETE FROM SCORE_LIST WHERE STU_ID = ? AND TEST_ID = ?"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
            ps.setInt(1, sl.getStuId());
            ps.setInt(2, sl.getTestId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public void updScoreList(ScoreList oldSl, ScoreList newSl) {
        String sql = "UPDATE SCORE_LIST SET STU_ID = ?, TEST_ID = ?, SCORE = ?, FEEDBACK = ? WHERE STU_ID = ? AND TEST_ID = ?"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            //set parameter of sql
            ps.setInt(1, newSl.getStuId());
            ps.setInt(2, newSl.getTestId());
            ps.setInt(3, newSl.getScore());
            ps.setString(4, newSl.getFeedBack());
            ps.setInt(5, oldSl.getStuId());
            ps.setInt(6, oldSl.getTestId());
            ps.execute();
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
    public List<ScoreList> searchByStudent(Student stu) {
        String sql = "SELECT * FROM SCORE_LIST WHERE STU_ID=?";
        try{
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1,stu.getId());
            rs = ps.executeQuery(sql);
            List<ScoreList> ans = new ArrayList<>();
            while(rs.next()){
                int stuId=rs.getInt("STU_ID");
                int testId=rs.getInt("TEST_ID");
                int score=rs.getInt("SCORE");
                String feedback=rs.getString("FEEDBACK");
                ScoreList result = new ScoreList(stuId,testId,score,feedback);
                ans.add(result);
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
    public List<ScoreList> searchByExam(Exam ex) {
        String sql = "SELECT * FROM SCORE_LIST WHERE TEST_ID = ?";
        try{
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1,ex.getTestId());
            rs = ps.executeQuery(sql);
            List<ScoreList> ans = new ArrayList<>();
            while(rs.next()){
                int stuId=rs.getInt("STU_ID");
                int testId=rs.getInt("TEST_ID");
                int score=rs.getInt("SCORE");
                String feedback=rs.getString("FEEDBACK");
                ScoreList result = new ScoreList(stuId,testId,score,feedback);
                ans.add(result);
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
