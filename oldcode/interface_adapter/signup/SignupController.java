package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String location, String group, boolean isNewGroup) {
        SignupInputData signupInputData = new SignupInputData(username, location, group, isNewGroup);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
