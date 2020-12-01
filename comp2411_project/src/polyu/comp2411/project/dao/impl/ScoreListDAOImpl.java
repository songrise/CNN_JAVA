package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.ScoreListDAO;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.ScoreList;
import polyu.comp2411.project.entity.Student;

import java.sql.Connection;
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
        return null;
    }

    @Override
    public void addScoreList(ScoreList sl) {
        String sql = "INSERT INTO SCORE_LIST VALUES(?,?,?,?)"; // parameter to be set later
        try {
            setPs(sql);
            //set parameter of sql
            getPs().setInt(1, sl.getStuId());
            getPs().setInt(2,sl.getTestId());
            getPs().setInt(3,sl.getScore());
            getPs().setString(4,sl.getFeedBack());
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
    public void delScoreList(ScoreList sl) {

    }

    @Override
    public void updScoreList(ScoreList oldSl, ScoreList newSl) {

    }

    @Override
    public List<ScoreList> searchByStudent(Student stu) {
        String sql = "SELECT * FROM SCORE_LIST WHERE STU_ID=?";
        try{
            setPs(sql);
            getPs().setInt(1,stu.getId());
            rs = getPs().executeQuery(sql);
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
