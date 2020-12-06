package polyu.comp2411.project.view;

import polyu.comp2411.project.controller.AccountManager;

import java.util.Scanner;

public class LoginView {
    private int uid = -1;
    public void view(){
        System.out.println("**Welcome to Automated Exam System!**");
        AccountManager accountManager = new AccountManager();
        System.out.println("**********************************");
        System.out.println("1: Login");
        System.out.println("2: Register");
        Scanner sc = new Scanner(System.in);
        System.out.println("**********************************");
        int op = -1;
        while (op !=1 && op !=2){
            System.out.print("please indecate your option: ");
            op = Integer.parseInt(sc.nextLine());
        }
        try {
            if (op == 1)
                uid = accountManager.login();
            else if(op == 2){
                accountManager.register();
                System.out.println("Successfully registered.");
                view();
            }
        }catch (Exception e){
            System.out.println("Error: "+e +"please contact admin!");
        }
//        finally {
//            sc.close();
//        }
    }

    public static void main(String[] args) {
        LoginView login = new LoginView();
        login.view();
    }

    public int getUid() {
        return uid;
    }

    public int getPriviledge(){
        if (uid>=0){
            if (uid<100000){
                return 0;
            }
            if (uid<300000){
                return 1;
            }
            if(uid<999999){
                return 2;
            }
        }
        return -1;
    }
}
