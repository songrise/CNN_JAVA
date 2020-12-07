package polyu.comp2411.project.view;

import polyu.comp2411.project.controller.TestDesigner;
import polyu.comp2411.project.dao.impl.ClasseDAOImpl;
import polyu.comp2411.project.dao.impl.DAOException;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.StudentAnswer;
import polyu.comp2411.project.service.ExamGradeService;
import polyu.comp2411.project.service.ManualJudgeService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.service.impl.ExamGradeServiceImpl;
import polyu.comp2411.project.service.impl.ManualJudgeServiceImpl;
import polyu.comp2411.project.service.impl.PerformanceAnalysisServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TeacherView {
    private int uid;
    public TeacherView(int uid){
        this.uid = uid;
    }

    public void teacherView(){
        System.out.println("**Welcome to Teacher System!**");
        System.out.println("******************************");
        System.out.println("1: Arrange New Exam");
        System.out.println("2: Manual Judge");
        System.out.println("3: Release Exam Result");
        System.out.println("4: Performance Analysis");
        System.out.println("5: Give Feedback");
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************");
        int op = -1;
        while (op !=1 && op !=2 && op !=3 && op !=4 && op!=5){
            System.out.print("Please input your option:");
            op = Integer.parseInt(sc.nextLine());
        }
        try {
            if (op == 1){
                TestDesigner testDesigner=new TestDesigner();
                System.out.println("********************************");
                System.out.println("1: Create New Exam");
                System.out.println("2: Add question to existing exam");
                System.out.println("3: Back to previous");
                System.out.println("********************************");
                op = -1;
                while (op !=1 && op !=2 && op !=3){
                    System.out.print("Please input your option: ");
                    op = sc.nextInt();
                    sc.nextLine();
                }
                if (op == 1){
                    testDesigner.createTest(uid);
                    System.out.print("\nWould you like to add question now? (Y/n): ");
                    String option = sc.nextLine();
                    if (option.equals("Y"))
                        testDesigner.createQuestions();
                    teacherView();
                }
                else if(op == 2){
                    testDesigner.createQuestions();
                    teacherView();
                }
                else if(op == 3){
                    teacherView();
                }

            }
                //accountManager.login();
            else if(op == 2){
                ManualJudgeService manualJudge=new ManualJudgeServiceImpl();
                int testId;
                System.out.print("\nPlease input the test id you would like judge: ");
                testId = sc.nextInt();
                sc.nextLine();

                ExamDAOImpl examDAO=new ExamDAOImpl();
                Exam ex= examDAO.searchByID(testId);
                List<StudentAnswer> studentAnswerList=manualJudge.fetchFLofAnExam(ex);
                if(!studentAnswerList.isEmpty()){
                    for(StudentAnswer studentAnswer:studentAnswerList){
                        System.out.println("\n********************************");
                        System.out.println("Below is the student's answer of question "+ studentAnswer.getQueNo()+": ");
                        System.out.println(studentAnswer.getAnswer());
                        System.out.print("Please enter the score of this answer: ");
                        int score = sc.nextInt();
                        sc.nextLine();
                        manualJudge.manualJudgeAQuesion(studentAnswer,score);
                }}else{
                        System.out.println("\nThere is no question in this exam to manual judge.\n You may release the result now.");
                }
                System.out.println("********************************");
                teacherView();
            }
            else if(op == 3){
                System.out.print("Please enter the id of the exam, make sure that \nyou have manual judged all FL question: ");
                int id = sc.nextInt();
                sc.nextLine();
                ExamGradeService examGradeService =new ExamGradeServiceImpl();
                examGradeService.calScoreOfExam(id);
                System.out.println("The results has been released to students.\n");
                teacherView();
            }
            else if(op == 4){ //TODO
                System.out.print("Please enter the id of the class you want to see the report: ");
                int id=sc.nextInt();
                sc.nextLine();
                System.out.println("********************************");
                ClasseDAOImpl classeDAO= new ClasseDAOImpl();
                Classe cls=classeDAO.searchById(id);
                PerformanceAnalysisServiceImpl performanceAnalysisService=new PerformanceAnalysisServiceImpl();
                Map<String, Double> subjectAvgs=performanceAnalysisService.subjectAvgs(cls);
                System.out.println("Below are the preformance analysis of the class:");
                System.out.println(subjectAvgs);
                teacherView();
            }else if(op == 5){
                System.out.print("Please enter the id of exam that you want to give feedback: ");
                int tid=sc.nextInt();
                sc.nextLine();
                System.out.print("Please enter the id of student that you want to give feedback: ");
                int sid=sc.nextInt();
                sc.nextLine();
                String fdbk;
                System.out.print("Please input your feedback(in one line): ");
                fdbk = sc.nextLine();
                ExamGradeService examGradeService = new ExamGradeServiceImpl();
                examGradeService.addFeedback(tid,sid,fdbk);

                System.out.println("Successfully added feedback!");
                System.out.println("******************************");
                teacherView();
            }
            
        }catch (DAOException | ServiceException e){
            System.out.println("Error: "+e +" please contact admin!");
            teacherView();
        }catch (Exception e){
            System.out.println("Unexpected error: "+e +", program terminated, please contact admin!");
        }
    }
    public static void main(String[] args) {
        TeacherView teacherView = new TeacherView(1);
        teacherView.teacherView();
    }
}
