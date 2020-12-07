package polyu.comp2411.project.controller;

import polyu.comp2411.project.service.ExamListService;
import polyu.comp2411.project.service.impl.ExamListServiceImpl;

import java.util.Map;

public class ExamListDisplay {
    public static void showExamList(int stuid) {
        ExamListService examListService = new ExamListServiceImpl();
        Map<Integer, String> schedule = examListService.getUpcomingTest(stuid);
        if (schedule.isEmpty()) {
            System.out.println("Seems you have no upcoming exam\n\n");
        } else {
            System.out.print("Test ID\t\t\tStart Time\n");
            for (Integer recordID : schedule.keySet()) {
                System.out.printf("%06d\t\t\t\t%s\n", recordID, schedule.get(recordID));
            }
        }
    }
}
