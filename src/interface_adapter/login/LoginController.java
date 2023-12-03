package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {
    final LoginInputBoundary userLoginUseCaseInteractor;
    public LoginController(LoginInputBoundary userLoginUseCaseInteractor) {
        this.userLoginUseCaseInteractor = userLoginUseCaseInteractor;
    }

    public void execute(String username, String location, String groupName, String condition, boolean groupCondition, String text) {
        LoginInputData loginInputData = new LoginInputData(username,location, groupName, condition, groupCondition, text);

        userLoginUseCaseInteractor.execute(loginInputData);
    }
}
