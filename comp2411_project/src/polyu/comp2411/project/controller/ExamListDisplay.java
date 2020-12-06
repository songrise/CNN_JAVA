package polyu.comp2411.project.controller;

import polyu.comp2411.project.dao.StudentDAO;
import polyu.comp2411.project.dao.impl.StudentDAOImpl;
import polyu.comp2411.project.entity.ScoreList;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.service.ExamListService;
import polyu.comp2411.project.service.ScoreListService;
import polyu.comp2411.project.service.impl.ExamListServiceImpl;
import polyu.comp2411.project.service.impl.ScoreListServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExamListDisplay {

        public static void showExamList(int stuid) {

            ExamListService examListService = new ExamListServiceImpl();
            Map<Integer, String> schedule = examListService.getUpcomingTest(stuid);
            if (schedule.isEmpty()) {
                System.out.println("Seems you have no upcoming exam\n");
            } else {
                System.out.print("Test ID\t\t\tStart Time\n");
                for (Integer recordID : schedule.keySet()) {
                    System.out.printf("%d\t\t\t\t%s\n", recordID, schedule.get(recordID));
                }
            }
        }

}
