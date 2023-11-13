package use_case.login;

import entity.User;

public class LoginInteractor implements LoginInputBoundary{
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface, LoginOutputBoundary loginOutputBoundary){
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }
    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        if (!userDataAccessObject.existsByName(username)){
            loginPresenter.prepareFailView(username + ": Account does not exist");
        }else{
            User user = userDataAccessObject.get(loginInputData.getUsername());
            LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(),false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }

    }
}
