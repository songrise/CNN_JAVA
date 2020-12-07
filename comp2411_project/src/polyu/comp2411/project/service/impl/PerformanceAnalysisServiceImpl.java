package polyu.comp2411.project.service.impl;

import polyu.comp2411.project.dao.ExamDAO;
import polyu.comp2411.project.dao.ScoreListDAO;
import polyu.comp2411.project.dao.SubjectDAO;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.dao.impl.ScoreListDAOImpl;
import polyu.comp2411.project.dao.impl.SubjectDAOImpl;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.ScoreList;
import polyu.comp2411.project.entity.Subject;
import polyu.comp2411.project.service.PerformanceAnalysisService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.util.TransactionUtil;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * provide service for class teacher to view and export performance analysis.
 */
public class PerformanceAnalysisServiceImpl implements PerformanceAnalysisService {

    @Override
    public Map<String, Double> subjectAvgs(Classe cls) {
        if (cls == null) {
            throw new ServiceException();
        }
        try{
            Connection conn = TransactionUtil.getConn();
            TransactionUtil.startTransaction();
            SubjectDAO subjectDAO = new SubjectDAOImpl(conn);
            ScoreListDAO scoreListDAO = new ScoreListDAOImpl(conn);
            ExamDAO examDAO = new ExamDAOImpl(conn);

            List<Subject> subs = subjectDAO.searchByClass(cls); //get all subjects in this class.
            Map<String,Double> avgScoreMap = new HashMap<>();

            for (Subject s:subs){
                List<Exam> examsOfThisSubInThisClass = examDAO.searchBySubAndClass(s,cls);

                int scoreSum = 0, numberOfStu = 0;
                for (Exam ex:examsOfThisSubInThisClass){
                    List<ScoreList> scoreListsOfThisSubInThisClass =scoreListDAO.searchByExam(ex);
                    for(ScoreList sl:scoreListsOfThisSubInThisClass) { //get all scores in this exam in this class on this subject
                        scoreSum+=sl.getScore();
                        numberOfStu+=1;
                    }
                    //next calculate avg.
                    double avg = ((double) scoreSum) / numberOfStu;
                    avgScoreMap.put(s.getName(),avg);
                }
            }
            TransactionUtil.commit();
            return avgScoreMap;
        }catch (Exception e){
            e.printStackTrace();

            TransactionUtil.rollBack();
            throw new ServiceException(e.getMessage());
        }finally {
            TransactionUtil.closeConn();
        }
    }

    @Override
    public Map<String, Double> subjectVars(Classe cls) {
        return null;
    }

    @Override
    public void exportAnalysis(Map<String, Double> analysis) {

    }

}
