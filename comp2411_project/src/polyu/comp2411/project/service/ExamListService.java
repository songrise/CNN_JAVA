package polyu.comp2411.project.service;

import java.util.Map;

public interface ExamListService {
    Map<Integer,String> getUpcomingTest(int stuId);
}
