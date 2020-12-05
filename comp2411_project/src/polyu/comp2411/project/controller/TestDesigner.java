package polyu.comp2411.project.controller;

import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Subject;
import polyu.comp2411.project.entity.Teacher;
import polyu.comp2411.project.service.TestDesignerService;
import polyu.comp2411.project.service.impl.TestDesignerServiceImpl;


import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Scanner;


/**
 * teacher use this to design a test.
 */
public class TestDesigner {
  void createTest(Teacher arranger){
    Scanner sc=new Scanner(System.in);
    TestDesignerService testDesignerService = new TestDesignerServiceImpl();
    int clsId, subId,duration;
    String startTime;


    System.out.printf("Please input the class id: ");
    clsId = sc.nextInt();
    sc.nextLine();

    System.out.printf("Please input the subject id: ");
    subId = sc.nextInt();
    sc.nextLine();

    System.out.printf("Please input the test duration in minutes: ");
    duration = sc.nextInt();
    sc.nextLine();

    System.out.printf("Please input the test start time format:yyyy-[m]m-[d]d hh:mm:ss[.f...] : ");
    startTime = sc.nextLine();
    int examId = testDesignerService.createTest(arranger.getId(),clsId,subId,duration,startTime);
    System.out.printf("Successfully created test with id = "+examId);
  }
  
  
  
  
  public void createQuestions(){
    Scanner sc=new Scanner(System.in);
    TestDesignerService testDesignerService = new TestDesignerServiceImpl();
    int qCount = 0;
    System.out.printf("Please input the ID of exam: ");
    int examId = sc.nextInt();

    while (true){
      String description,type,answer;
      boolean isCompulsory;
      int score;

      System.out.printf("Please input the description of the question: ");
//      if (sc.hasNextLine())
        description = sc.nextLine();

      System.out.printf("Please input whether this question is compulsory: (yes/no)");
      isCompulsory = sc.nextBoolean();


      System.out.printf("Please input this question's type, it is multiple choice, fill in the blank, or standard full-length test question: (MC/FB/FL)");
      type=sc.nextLine().toUpperCase();

      System.out.printf("Please input the answer of the question: ");

      answer=sc.nextLine();

      System.out.printf("Please input the score of the question: ");
      score = sc.nextInt();
      testDesignerService.createQuestion(examId,description,isCompulsory,type,answer,score);

      System.out.println("You have added "+qCount+ " questions, continue? (y/N): ");
      if (sc.nextByte() == 'N'){
        System.out.printf("Exited.");
        sc.close();
        break;
      }
    }
  }


  public static void main(String[] args) {
    Teacher test = new Teacher(000001,"Test");
    TestDesigner testDesigner = new TestDesigner();
    testDesigner.createTest(test);
    testDesigner.createQuestions();
  }
}
