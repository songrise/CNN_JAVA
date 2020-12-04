package polyu.comp2411.project.controller;

import polyu.comp2411.project.service.TestDesignerService;


import java.math.BigInteger;
import java.sql.Timestamp;
import java.util;
import java.lang;

/**
 * teacher use this to design a test.
 */
public class TestDesigner {
  int createTest(Teacher arranger, Classe cls, Subject sub, BigInteger testDuration, Timestamp startTime){
    system.out.printf("Please input the teacher name: ");
    Scanner sc=new Scanner(System.in);
    String arranger=new String();
    arranger=sc.nextLine();
    
    system.out.printf("Please input the class id: ");
    Scanner sc=new Scanner(System.in);
    String cls=new String();
    cls=sc.nextLine();
    
    system.out.printf("Please input the subject id: ");
    Scanner sc=new Scanner(System.in);
    String sub=new String();
    sub=sc.nextLine();
    
    system.out.printf("Please input the test duration: ");
		Scanner sc=new Scanner(System.in);
    BigInteger testDuration=sc.nextInt();
    
    system.out.printf("Please input the test start time: ");
		Scanner sc=new Scanner(System.in);
    Timestamp startTime=new Timestamp();
    
    return testId;
  
  }
  
  
  
  
  int createQuestion(Exam ex, String qDesc, boolean isCompulsory, String type, String answer, int score){
    system.out.printf("Please input the description of the question: ");
    Scanner sc=new Scanner(System.in);
    String qDesc=new String();
    qDesc=sc.nextLine();
    
    system.out.printf("Please input whether this question is compulsory: (yes/no)");
    Scanner sc=new Scanner(System.in);
    String a=new String();
    a=sc.nextLine();
    
    if (a.equals("yes")){
      isCompulsory=true;
    }
    if (a.equals("no")){
      isCompulsory=false;
    }
    
    system.out.printf("Please input this question's type, it is multiple choice, fill in the blank, or standard full-length test question: (MC/FB/FL)");
    Scanner sc=new Scanner(System.in);
    String type=new String();
    type=sc.nextLine();
    
    system.out.printf("Please input the answer of the question: ");
    Scanner sc=new Scanner(System.in);
    String answer=new String();
    answer=sc.nextLine();
    
    system.out.printf("Please input the score of the question: ");
    Scanner sc=new Scanner(System.in);
    int answer=sc.nextInt();
    
    return qNo;
  }
    
}
