package polyu.comp2411.project.dao.impl;

import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl extends BaseDAO implements QuestionDAO {
    private Question question;
    private ResultSet rs;

    private ResultSet result;
    public QuestionDAOImpl(){
        super();
    }
    public QuestionDAOImpl(Connection connection){
        super(connection);
    }

    @Override
    public Question searchByKey(int testId, int qNo) {
        String sql = "SELECT * FROM QUESTION WHERE TEST_ID = ? AND QUESTION_NO = ?";
        try{
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1,testId);
            ps.setInt(2,qNo);
            rs = ps.executeQuery();
            while(rs.next()){
                String description = rs.getString("Q_DESCRIPTION");
                boolean compulsory=rs.getBoolean("COMPULSORY");
                String type = rs.getString("TYPE");
                String answer = rs.getString("ANSWER");
                int score=rs.getInt("SCORE");
                Question result = new Question(qNo,testId,description,compulsory,type,answer,score);
                this.question= result;
                return result;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        finally {
            closeStatement();
            closePreparedStatement();
        }
         return null;
    }

    @Override
    public void delAllQuestionOfExam(Exam ex) {
        String sql = "DELETE TABLE QUESTION"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            // set parameter of sql
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
    public void addQuestion(Question que) {
        String sql = "INSERT INTO QUESTION VALUES(?,?,?,?,?,?,?)"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            //set parameter of sql
            ps.setInt(1,que.getqNo());
            ps.setInt(2,que.getTestId());
            ps.setString(3,que.getqDescri());
            ps.setBoolean(4,que.getCompulsory());
            ps.setString(5,que.getType());
            ps.setString(6,que.getAns());
            ps.setInt(7,que.getScore());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public void delQuestion(Question que) {
        String sql = "DELETE FROM QUESTION WHERE QUESTION_NO = ?"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            //set parameter of sql
            ps.setInt(1, que.getqNo());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public void updQuesiton(Question oldQue, Question newQue) {
        String sql = "UPDATE QUESTION SET QUESTION_NO = ?, TEST_ID = ?, Q.DESCRIPTION = ?, COMPULSORY = ?, TYPE = ?, ANSWER = ?, SCORE = ? WHERE QUESTION_NO = ?"; // parameter to be set later
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            //set parameter of sql
            ps.setInt(1, newQue.getqNo());
            ps.setInt(2, newQue.getTestId());
            ps.setString(3, newQue.getqDescri());
            ps.setBoolean(4, newQue.getCompulsory());
            ps.setString(5, newQue.getType());
            ps.setString(6, newQue.getAns());
            ps.setInt(7, newQue.getScore());
            ps.setInt(8, oldQue.getqNo());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
    }

    @Override
    public List<Question> searchByExam(Exam ex) {
        String sql = "SELECT * FROM Question WHERE TEST_ID=?";
        try{
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1,ex.getTestId());
            rs = ps.executeQuery(sql);
            List<Question> ans = new ArrayList<>();
            while(rs.next()){
                int questionNo=rs.getInt("QUESTION_NO");
                String description = rs.getString("Q_DESCRIPTION");
                boolean compulsory=rs.getBoolean("COMPULSORY");
                String type = rs.getString("TYPE");
                String answer = rs.getString("ANSWER");
                int score=rs.getInt("SCORE");
                Question result = new Question(questionNo,ex.getTestId(),description,compulsory,type,answer,score);
                ans.add(result);
            }
            return ans;
        }catch (SQLException e){
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        finally {
            // close resources used
            closeStatement();
            closePreparedStatement();
        }
        // return null;
    }

    @Override
    public int getNextQuestionNo(Exam ex) {
            String sql = "SELECT MAX(Question_No) FROM QUESTION WHERE TEST_ID = ?";
            try {
                PreparedStatement ps = getConn().prepareStatement(sql);
                ps.setInt(1,ex.getTestId());
                // PreparedStatement ps = getConn().prepareStatement(sql);
                // rs = ps.executeQuery();
                rs = ps.executeQuery();

                while (rs.next()) {
                    return rs.getInt("MAX(Question_No)") + 1;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            } finally {
                // close resources used
                closeStatement();
                closePreparedStatement();

                // rs.close();
            }
            return -1;
        }

}
