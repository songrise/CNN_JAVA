package polyu.comp2411.project.controller;

import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.Question;
import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.entity.ExamList;
import polyu.comp2411.project.service.ExamService;
import polyu.comp2411.project.service.impl.ExamServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ExamSystem {
    /**
     * a student use this to sit an exam
     * @param stu
     */


    public void sitExam(Student stu, Exam ex){
        if(!stu.ExamList.testId.contains(ex.testId)){
            exit;
        }

        ExamService service = new ExamServiceImpl();
        List<Question> allQuestion = service.sitExam(stu, ex);
        for (Question q:allQuestion){
            if(isTimeUp==true){
                break;
            }
            System.out.printf("QNo:%d Type:%s, Compulsory:%s Score:%d \n",q.getqNo(),q.getType(),q.getCompulsory()?"y":"n",q.getScore());
            System.out.println("****************************************");
            System.out.println(q.getqDescri());
            System.out.println("****************************************");
            System.out.print("Input your answer(in one line, \"skip\" to skip): ");

            String answer;

            answer= sc.nextLine();
            if (answer.equals("skip")){
                if (!q.getCompulsory())
                    answer=null;
                else{
                    System.out.print("This question is compulsory, please input your answer: ");
                    answer= sc.nextLine();
                }
            }
            examService.answerAnQuestion(q, stuId,answer);
        }
        System.out.println("You have answered all question, submit now?(Y/n)");
        String op = sc.nextLine();
        if(op.equals("Y")){
            AutoJudgeService autoJudgeService = new AutoJudgeServiceImpl();
            autoJudgeService.judgeAnExam(testId);
        }
    }

    private boolean isTimeUp(int testId);

    public void answerAnQuestion(Question que, Student stu, String stuAnswerStr) {
        Scanner sc = new Scanner(System.in);
    }
}
