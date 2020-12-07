package polyu.comp2411.project.service;

public interface AccountService {
    //===========public===========

    /**
     * login into an account
     *
     * @param uid           uid of user, should be same as the id(stuId for student, teacherId for teacher)
     * @param inputPassword inputted password
     * @return priviledge, 0 for admin, 1 for teacher, 2 for student.
     */
    int login(int uid, String inputPassword);

    void register(int uid, String inputPassword, int priviledge);

    void changePassword(int uid, String newPassword);
}
