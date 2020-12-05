package polyu.comp2411.project.view;

import polyu.comp2411.project.controller.AccountManager;

import java.util.Scanner;

public class LoginView {
    public void view(){
        System.out.println("**Welcome to Automated Exam System!**");
        AccountManager accountManager = new AccountManager();
        System.out.println("********************************");
        System.out.println("1: Login");
        System.out.println("2: Register");
        Scanner sc = new Scanner(System.in);
        System.out.println("********************************");
        int op = -1;
        while (op !=1 && op !=2){
            System.out.println("please indecate your option: ");
            op = sc.nextInt();
            sc.nextLine();
        }
        try {
            if (op == 1)
                accountManager.login();
            else if(op == 2)
                accountManager.register();
        }catch (Exception e){
            System.out.println("Error: "+e+"please contact admin!");
        }
    }

    public static void main(String[] args) {
        LoginView login = new LoginView();
        login.view();
    }
}
