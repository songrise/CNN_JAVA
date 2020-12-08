package polyu.comp2411.project.view;

import polyu.comp2411.project.controller.TestDesigner;
import polyu.comp2411.project.dao.LoggerDAO;
import polyu.comp2411.project.dao.impl.ClasseDAOImpl;
import polyu.comp2411.project.dao.impl.DAOException;
import polyu.comp2411.project.dao.impl.ExamDAOImpl;
import polyu.comp2411.project.dao.impl.LoggerDAOImpl;
import polyu.comp2411.project.entity.Classe;
import polyu.comp2411.project.entity.Exam;
import polyu.comp2411.project.entity.StudentAnswer;
import polyu.comp2411.project.service.ExamGradeService;
import polyu.comp2411.project.service.ManualJudgeService;
import polyu.comp2411.project.service.ServiceException;
import polyu.comp2411.project.service.impl.ExamGradeServiceImpl;
import polyu.comp2411.project.service.impl.ManualJudgeServiceImpl;
import polyu.comp2411.project.service.impl.PerformanceAnalysisServiceImpl;
import polyu.comp2411.project.util.TransactionUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TeacherView {
    private int uid;

    public TeacherView(int uid) {
        this.uid = uid;
        long l = System.currentTimeMillis();
        Date d = new Date(l);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String info = "[Teacher: " + uid + "] log in system at: " + simpleDateFormat.format(d);

        LoggerDAO loggerDAO = new LoggerDAOImpl();
        loggerDAO.addLog(info);
        TransactionUtil.commit();
    }

    public void teacherView() {
        System.out.println("**Welcome to Teacher System!**");
        System.out.println("******************************");
        System.out.println("1: Arrange New Exam");
        System.out.println("2: Manual Judge");
        System.out.println("3: Release Exam Result");
        System.out.println("4: Performance Analysis");
        System.out.println("5: Give Feedback");
        System.out.println("******************************");

        Scanner sc = new Scanner(System.in);
        int op = -1;
        do {
            System.out.print("Please input your option:");
            try {
                String inpput = sc.nextLine().trim();
                if (inpput.length() == 1)
                    op = Integer.parseInt(inpput);
            } catch (NumberFormatException e) {
                System.out.println("please enter an integer");
            }
        } while (op < 1 || op > 5);

        try {
            if (op == 1) {
                TestDesigner testDesigner = new TestDesigner();

                System.out.println("********************************");
                System.out.println("1: Create New Exam");
                System.out.println("2: Add question to existing exam");
                System.out.println("3: Back to previous");
                System.out.println("********************************");

                op = -1;
                do {
                    System.out.print("Please input your option:");
                    try {
                        String inpput = sc.nextLine().trim();
                        if (inpput.length() == 1)
                            op = Integer.parseInt(inpput);
                    } catch (NumberFormatException e) {
                        System.out.println("please enter an integer");
                    }
                } while (op < 1 || op > 3);

                if (op == 1) {
                    testDesigner.createTest(uid);

                    while (true) {
                        System.out.print("\nWould you like to add question now? (Y/n): ");
                        String input = sc.nextLine().trim().toUpperCase();
                        if (input.equals("Y"))
                            testDesigner.createQuestions();
                        else if (!input.equals("N")) {
                            continue;
                        }
                        break;
                    }
                } else if (op == 2) {
                    testDesigner.createQuestions();
                }
                teacherView();
            }

            //accountManager.login();
            else if (op == 2) {
                ManualJudgeService manualJudge = new ManualJudgeServiceImpl();
                int testId;

                while (true) {
                    System.out.print("\nPlease input the test id you would like judge: ");
                    try {
                        String input = sc.nextLine().trim();
                        if (input.length() == 6) {
                            testId = Integer.parseInt(input);
                            break;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                    System.out.println("The id should be 6 digits!");
                }

                ExamDAOImpl examDAO = new ExamDAOImpl();
                Exam ex = examDAO.searchByID(testId);
                List<StudentAnswer> studentAnswerList = manualJudge.fetchFLofAnExam(ex);

                if (!studentAnswerList.isEmpty()) {
                    for (StudentAnswer studentAnswer : studentAnswerList) {
                        System.out.println("\n********************************");
                        System.out.println("Below is the student's answer of question " + studentAnswer.getQueNo() + ": ");
                        System.out.println(studentAnswer.getAnswer());

                        int score;
                        while (true) {
                            System.out.print("Please enter the score of this answer: ");
                            try {
                                score = Integer.parseInt(sc.nextLine().trim());
                                break;
                            } catch (NumberFormatException ignored) {
                            }
                            System.out.println("The score should be an integer!");
                        }
                        manualJudge.manualJudgeAQuesion(studentAnswer, score);
                    }
                } else {
                    System.out.println("\nThere is no question in this exam to manual judge.\n You may release the result now.");
                }

                System.out.println("********************************");
                teacherView();

            } else if (op == 3) {
                int id;
                while (true) {
                    System.out.print("Please enter the id of the exam, make sure that \nyou have manual judged all FL question: ");
                    try {
                        String input = sc.nextLine().trim();
                        if (input.length() == 6) {
                            id = Integer.parseInt(input);
                            break;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                    System.out.println("The id should be 6 digits!");
                }

                ExamGradeService examGradeService = new ExamGradeServiceImpl();
                examGradeService.calScoreOfExam(id);
                System.out.println("The results has been released to students.\n");

                teacherView();

            } else if (op == 4) {
                int id;
                while (true) {
                    System.out.print("Please enter the id of the class you want to see the report: ");
                    try {
                        String input = sc.nextLine().trim();
                        if (input.length() == 6) {
                            id = Integer.parseInt(input);
                            break;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                    System.out.println("The id should be 6 digits!");
                }

                System.out.println("********************************");
                ClasseDAOImpl classeDAO = new ClasseDAOImpl();
                Classe cls = classeDAO.searchById(id);
                PerformanceAnalysisServiceImpl performanceAnalysisService = new PerformanceAnalysisServiceImpl();
                Map<String, Double> subjectAvgs = performanceAnalysisService.subjectAvgs(cls);
                Map<String, Double> subjectVars = performanceAnalysisService.subjectVars(cls);
                StringBuilder sb = new StringBuilder("Below are the preformance analysis of the class "+id+":\n");
                sb.append("Subject\t\tAverage\t\tvariace:\n");
                for (String k : subjectAvgs.keySet()){
                    sb.append(String.format("%s,\t\t%.2f\t\t%.2f\n",k,subjectAvgs.get(k),subjectVars.get(k)));
                }
                System.out.println(sb.toString());
                System.out.print("Would you like to export the analysis? (y/n): ");
                if (sc.nextLine().toUpperCase().equals("Y")){
                    //todo
                }



                teacherView();

            } else {
                int tid;
                while (true) {
                    System.out.print("Please enter the id of exam that you want to give feedback: ");
                    try {
                        String input = sc.nextLine().trim();
                        if (input.length() == 6) {
                            tid = Integer.parseInt(input);
                            break;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                    System.out.println("The id should be 6 digits!");
                }

                int sid;
                while (true) {
                    System.out.print("Please enter the id of student that you want to give feedback: ");
                    try {
                        String input = sc.nextLine().trim();
                        if (input.length() == 6) {
                            sid = Integer.parseInt(input);
                            break;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                    System.out.println("The id should be 6 digits!");
                }

                String fdbk;
                System.out.print("Please input your feedback(in one line): ");
                fdbk = sc.nextLine();

                ExamGradeService examGradeService = new ExamGradeServiceImpl();
                examGradeService.addFeedback(tid, sid, fdbk);

                System.out.println("Successfully added feedback!");
                System.out.println("******************************");
                teacherView();
            }
        } catch (DAOException | ServiceException e) {
            System.out.println("Error: " + e + " please contact admin!");
            teacherView();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e + ", program terminated, please contact admin!");
        }
    }

    public static void main(String[] args) {
        TeacherView teacherView = new TeacherView(1);
        teacherView.teacherView();
    }
}
