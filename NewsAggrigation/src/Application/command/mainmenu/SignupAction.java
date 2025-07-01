package Application.command.mainmenu;

import Application.auth.login.LoginResponse;
import Application.auth.signup.SignupRequest;
import Application.auth.signup.SignupResponse;
import Application.auth.signup.service.SignupService;
import Application.command.MenuAction;

import java.util.Scanner;

public class SignupAction implements MenuAction {

    private final Scanner scanner = new Scanner(System.in);
    private final SignupService signupService = new SignupService();

    @Override
    public String getName() {
        return "Signup";
    }

    @Override
    public void execute(LoginResponse ignored) {
        SignupRequest signupRequest = collectSignupInput();
        SignupResponse signupResponse = performSignup(signupRequest);
        displaySignupResult(signupResponse);
    }

    private SignupRequest collectSignupInput() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        // Future extension: allow role selection
        String role = "User";

        SignupRequest request = new SignupRequest();
        request.setEmail(email);
        request.setUsername(username);
        request.setPassword(password);
        request.setRole(role);
        return request;
    }

    private SignupResponse performSignup(SignupRequest request) {
        try {
            return signupService.signup(request);
        } catch (Exception e) {
            System.err.println("Error during signup: " + e.getMessage());
            return null;
        }
    }

    private void displaySignupResult(SignupResponse response) {
        if (response == null) {
            System.out.println("Signup failed due to internal error.");
        } else if (response.getStatus() == 200) {
            System.out.println("User created successfully.");
        } else {
            System.out.println("Signup failed: " + response.getMessage());
        }
    }
}
