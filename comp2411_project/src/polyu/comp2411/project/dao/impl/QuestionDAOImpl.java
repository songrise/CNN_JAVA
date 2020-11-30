package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl extends BaseDAO implements QuestionDAO {
    private Question question;
    private ResultSet rs;

    private ResultSet result;
    QuestionDAOImpl(){
        super();
    }

    @Override
    public Question searchByID(int id) {
        String sql = "SELECT * FROM QUESTION WHERE QUESTION_NO = ?";
        try{
            setPs(sql);
            getPs().setInt(1,id);
            rs = getPs().executeQuery();
            while(rs.next()){
                int testId=rs.getInt("TEST_ID");
                String description = rs.getString("Q_DESCRIPTION");
                boolean compulsory=rs.getBoolean("COMPULSORY");
                String type = rs.getString("TYPE");
                String answer = rs.getString("ANSWER");
                int score=rs.getInt("SCORE");
                Question result = new Question(id,testId,description,compulsory,type,answer,score);
                this.question= result; //set the teacher field as this.
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
    public void addQuestion(Question que) {
        String sql = "INSERT INTO QUESTION VALUES(?,?,?,?,?,?,?)"; // parameter to be set later
        try {
            setPs(sql);
            //set parameter of sql
            getPs().setInt(1,que.getqNo());
            getPs().setInt(2,que.getTestId());
            getPs().setString(3,que.getqDescri());
            getPs().setBoolean(4,que.getCompulsory());
            getPs().setString(5,que.getType());
            getPs().setString(6,que.getAns());
            getPs().setInt(7,que.getScore());
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
    public void delQuestion(Question que) {
        String sql = "DELETE FROM QUESTION WHERE QUESTION_NO = ?"; // parameter to be set later
        try {
            setPs(sql);
            //set parameter of sql
            getPs().setInt(1, que.getqNo());
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
    public List<Question> searchByExam(Exam ex) {
        String sql = "SELECT * FROM Question WHERE TEST_ID=?";
        try{
            setPs(sql);
            getPs().setInt(1,ex.getTestId());
            rs = getPs().executeQuery(sql);
            List<Question> ans = new ArrayList<>();
            while(rs.next()){
                int questionNo=rs.getInt("QUESTION_NO");
                int testId=rs.getInt("TEST_ID");
                String description = rs.getString("Q_DESCRIPTION");
                boolean compulsory=rs.getBoolean("COMPULSORY");
                String type = rs.getString("TYPE");
                String answer = rs.getString("ANSWER");
                int score=rs.getInt("SCORE");
                Question result = new Question(questionNo,testId,description,compulsory,type,answer,score);
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
}
