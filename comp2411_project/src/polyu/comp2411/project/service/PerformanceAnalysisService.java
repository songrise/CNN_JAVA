package polyu.comp2411.project.service;

import polyu.comp2411.project.entity.Classe;

import java.util.Map;

/**
 * provide service for performance analysis. different kinds of analysis are avialiable
 */
public interface PerformanceAnalysisService {
    //todo

    /**
     * calculate for all subjects offered in this class the averange score of it
     * @param cls
     * @return a map contained subjects and its corresponding avg. e.g.: <"Math", 85.4>
     */
    Map<String,Double> subjectAvgs(Classe cls);

    /**
     * calculate for all subjects offered in this class the variance of score.
     * @param cls
     * @return a map contained subjects and its corresponding var. e.g.: <"Math", 14.9>
     */
    Map<String,Double> subjectVars(Classe cls);

    /**
     * export an anlysis report. todo
     * @param analysis
     */
    void exportAnalysis(Map<String,Double> analysis);
}
