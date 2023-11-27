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
        String condition = loginInputData.getCondition();
        String location = loginInputData.getLocation();
        //
        System.out.println(loginInputData.getLocation());

        LoginOutputData loginOutputData;
        if (!userDataAccessObject.existsByName(username) && condition != "sign up"){
            loginPresenter.prepareFailView(username + ": Account does not exist");
        }else if(condition == "sign up"){
            loginOutputData = new LoginOutputData(username,location,false);
            loginPresenter.prepareSuccessView(loginOutputData);}
        else{
            User user = userDataAccessObject.get(loginInputData.getUsername());
            loginOutputData = new LoginOutputData(user.getUsername(), user.getLocation(),false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }

    }
}
