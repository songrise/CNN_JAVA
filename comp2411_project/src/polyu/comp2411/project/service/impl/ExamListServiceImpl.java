package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.dao.ExamListDAO;
import polyu.comp2411.project.dao.QuestionDAO;
import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.dao.impl.ExamListDAOImpl;
import polyu.comp2411.project.dao.impl.QuestionDAOImpl;
import polyu.comp2411.project.dao.impl.StudentDAOImpl;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.ExamList;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.service.ExamListService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.util.TransactionUtil;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamListServiceImpl implements ExamListService {
    @Override
    public Map<Integer, String> getUpcomingTest(int stuId) {
        if (stuId<0) {
            throw new ServiceException();
        }
        try{
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            ExamListDAO examListDAO = new ExamListDAOImpl(conn);
            StudentDAO studentDAO = new StudentDAOImpl(conn);
            ExamDAO examDAO = new ExamDAOImpl(conn);


            Student stu=studentDAO.searchByID(stuId);
            List<ExamList> examList=examListDAO.searchByStudent(stu);
            Map<Integer,String> result = new HashMap<>();
            for (ExamList record : examList) {
                Exam e = examDAO.searchByID(record.getTestId());
                String timeStr = e.getStartTime().toString();
                result.put(record.getTestId(),timeStr);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());
        }finally {
            TransactionUtil.closeConn();
        }

    }
}
