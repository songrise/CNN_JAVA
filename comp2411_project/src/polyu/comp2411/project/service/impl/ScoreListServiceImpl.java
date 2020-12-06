package polyu.comp2411.project.service.impl;


import polyu.comp2411.project.dao.ScoreListDAO;
import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.dao.impl.ScoreListDAOImpl;
import polyu.comp2411.project.dao.impl.StudentDAOImpl;
import polyu.comp2411.project.entity.ScoreList;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.service.ScoreListService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.util.TransactionUtil;

import java.sql.Connection;
import java.util.List;

public class ScoreListServiceImpl implements ScoreListService {
    @Override
    public List<ScoreList> getScoreList(int stuID) {
        if (stuID<0){
            throw new ServiceException();
        }
        try{
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            ScoreListDAO scoreListDAO = new ScoreListDAOImpl(conn);
            StudentDAO studentDAO = new StudentDAOImpl(conn);

            Student stu = studentDAO.searchByID(stuID);
            List<ScoreList> sl = scoreListDAO.searchByStudent(stu); //get all subjects in this class.

            TransactionUtil.commit();
            return sl;
        }catch (Exception e){
            e.printStackTrace();
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());
        }finally {
            TransactionUtil.closeConn();
        }

    }
}
