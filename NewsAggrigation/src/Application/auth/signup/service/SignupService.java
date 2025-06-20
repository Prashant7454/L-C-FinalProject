package Application.auth.signup.service;

import Application.auth.signup.SignupRequest;
import Application.auth.signup.controller.SignupController;

public class SignupService {
    public Application.auth.signup.SignupResponse signup(SignupRequest signupRequest) throws Exception{
        SignupController signupController = new SignupController();
        return signupController.signup(signupRequest);
    }
}
