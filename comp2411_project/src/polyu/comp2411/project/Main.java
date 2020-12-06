package polyu.comp2411.project;

import polyu.comp2411.project.entity.Student;
import polyu.comp2411.project.view.AdminView;
import polyu.comp2411.project.view.LoginView;
import polyu.comp2411.project.view.StudentView;
import polyu.comp2411.project.view.TeacherView;

public class Main {

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.view();
        int uid = loginView.getUid();
        int priviledge = loginView.getPriviledge();
        switch (priviledge){
            case 0:
                AdminView adminView = new AdminView(uid);

                break;
            case 1:
                TeacherView teacherView = new TeacherView(uid);
                teacherView.teacherView();
                break;
            case 2:
                StudentView studentView = new StudentView(uid);
                studentView.stuView();
                break;

        }
    }
}
