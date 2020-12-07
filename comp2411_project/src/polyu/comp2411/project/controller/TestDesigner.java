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
    public void createTest(int arrangerId) {
        Scanner sc = new Scanner(System.in);
        TestDesignerService testDesignerService = new TestDesignerServiceImpl();
        int clsId, subId, duration;
        String startTime;

        while (true) {
            System.out.print("Please input the class id: ");
            try {
                String input = sc.nextLine().trim();
                if (input.length() == 6) {
                    clsId = Integer.parseInt(input);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("The id should be 6 digits!");
        }

        while (true) {
            System.out.print("Please input the subject id: ");
            try {
                String input = sc.nextLine().trim();
                if (input.length() == 6) {
                    subId = Integer.parseInt(input);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("The id should be 6 digits!");
        }

        while (true) {
            System.out.print("Please input the test duration in minutes: ");
            try {
                duration = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("The duration should be an integer!");
        }

        System.out.printf("Please input the test start time format:yyyy-[m]m-[d]d hh:mm:ss[.f...] : ");
        startTime = sc.nextLine();

        int examId = testDesignerService.createTest(arrangerId, clsId, subId, duration, startTime);
        if (examId == -1) {
            throw new ServiceException("You cannot create this exam!");
        }
        System.out.printf("Successfully created test with id = " + examId);
    }

    public void createQuestions() {
        Scanner sc = new Scanner(System.in);
        TestDesignerService testDesignerService = new TestDesignerServiceImpl();
        int qCount = 0;

        int examId;
        while (true) {
            System.out.print("\nPlease input the ID of exam: ");
            try {
                String input = sc.nextLine().trim();
                if (input.length() == 6) {
                    examId = Integer.parseInt(input);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("The id should be 6 digits!");
        }

        while (true) {
            String description, type, answer;
            boolean isCompulsory = false;
            int score;

            System.out.printf("Please input the description of the question: ");
//      if (sc.hasNextLine())
            description = sc.nextLine();

            while (true) {
                System.out.printf("Please input whether this question is compulsory(yes/no): ");
                String input = sc.nextLine().trim().toUpperCase();
                if (input.equals("YES"))
                    isCompulsory = true;
                else if (!input.equals("NO"))
                    continue;
                break;
            }

            while (true) {
                System.out.printf("Please input this question's type, it is multiple choice, \nfill in the blank, or standard full-length test question (MC/FB/FL): ");
                String input = sc.nextLine().trim().toUpperCase();
                if (input.equals("MC") || input.equals("FB") || input.equals("FL")) {
                    type = input;
                    break;
                }
            }

            System.out.printf("Please input the answer of the question: ");
            answer = sc.nextLine();

            while (true) {
                System.out.print("Please input the score of the question: ");
                try {
                    score = Integer.parseInt(sc.nextLine().trim());
                    break;
                } catch (NumberFormatException ignored) {
                }
                System.out.println("The score should be an integer!");
            }

            testDesignerService.createQuestion(examId, description, isCompulsory, type, answer, score);
            qCount += 1;

            boolean isContinue = false;
            while (true) {
                System.out.print("You have added " + qCount + " questions, continue? (Y/N): ");
                String input = sc.nextLine().trim().toUpperCase();

                if (input.equals("Y"))
                    isContinue = true;
                else if (!input.equals("N"))
                    continue;
                break;
            }

            if (!isContinue) {
                System.out.printf("Exited.\n");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Teacher test = new Teacher(000001, "Test");
        TestDesigner testDesigner = new TestDesigner();
//  testDesigner.createTest(test);
        testDesigner.createQuestions();
    }
}
