package polyu.comp2411.project.controller;

import polyu.comp2411.project.entity.Teacher;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.service.TestDesignerService;
import polyu.comp2411.project.service.impl.TestDesignerServiceImpl;

import java.util.Scanner;


/**
 * teacher use this to design a test.
 */
public class TestDesigner {
  public void createTest(int arrangerId){
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
    int examId = testDesignerService.createTest(arrangerId,clsId,subId,duration,startTime);
    if (examId == -1){
      throw new ServiceException("You cannot create this exam!");
    }
    System.out.printf("Successfully created test with id = "+examId);
  }
  
  
  
  
  public void createQuestions(){
    Scanner sc=new Scanner(System.in);
    TestDesignerService testDesignerService = new TestDesignerServiceImpl();
    int qCount = 0;
    System.out.printf("\nPlease input the ID of exam: ");
    int examId = sc.nextInt();
    sc.nextLine();

    while (true){
      String description,type,answer;
      boolean isCompulsory = false;
      int score;

      System.out.printf("Please input the description of the question: ");
//      if (sc.hasNextLine())
        description = sc.nextLine();

      System.out.printf("Please input whether this question is compulsory(yes/no): ");
      String s = sc.nextLine();
      if(s.toUpperCase().equals("YES"))
        isCompulsory = true;



      System.out.printf("Please input this question's type, it is multiple choice, \nfill in the blank, or standard full-length test question (MC/FB/FL): ");
      type=sc.nextLine().toUpperCase();

      System.out.printf("Please input the answer of the question: ");

      answer=sc.nextLine();

      System.out.printf("Please input the score of the question: ");
      score = sc.nextInt();
      sc.nextLine();
      testDesignerService.createQuestion(examId,description,isCompulsory,type,answer,score);
      qCount+=1;
      System.out.print("You have added "+qCount+ " questions, continue? (y/N): ");
      String ifContinue = sc.nextLine();
      if (ifContinue.toUpperCase().equals("N")){
        System.out.printf("Exited.\n");
        break;
      }
    }
  }


  public static void main(String[] args) {
    Teacher test = new Teacher(000001,"Test");
    TestDesigner testDesigner = new TestDesigner();
//  testDesigner.createTest(test);
    testDesigner.createQuestions();
  }
}
