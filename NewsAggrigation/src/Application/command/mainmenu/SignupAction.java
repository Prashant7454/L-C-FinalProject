package Application.command.mainmenu;

import Application.auth.signup.SignupRequest;
import Application.auth.signup.SignupResponse;
import Application.auth.signup.service.SignupService;
import Application.command.MenuAction;

import java.util.Scanner;

public class SignupAction implements MenuAction {

    Scanner scanner = null;

    public SignupAction(){
        scanner = new Scanner(System.in);
    }
    @Override
    public String getName() {
        return "Signup";
    }

    @Override
    public void execute(int userId) {
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();
        String role = "User";

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail(email);
        signupRequest.setPassword(password);
        signupRequest.setUsername(username);
        signupRequest.setRole(role);

        SignupService signupService = new SignupService();

        SignupResponse signupResponse = null;
        try{
            signupResponse = signupService.signup(signupRequest);
        }
        catch (Exception e){
            return;
        }
        if(signupResponse.getStatus() == 200){
            System.out.println("User Created Successfully...");
        }
        else{
            System.out.println("error: " + signupResponse.getMessage());
        }

    }

}
