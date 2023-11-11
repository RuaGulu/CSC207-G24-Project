package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {
    final LoginInputBoundary userLoginUseCaseInteractor;
    public LoginController(LoginInputBoundary userLoginUseCaseInteractor) {
        this.userLoginUseCaseInteractor = userLoginUseCaseInteractor;
    }

    public void execute(String username) {
        LoginInputData loginInputData = new LoginInputData(username);

        userLoginUseCaseInteractor.execute(loginInputData);
    }
}
