package use_case.login;

import entity.Group;
import entity.GroupFactory;
import entity.User;
import use_case.group.GroupDataAccessInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class LoginInteractor implements LoginInputBoundary{
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    final GroupFactory groupFactory;
    final GroupDataAccessInterface groupDataAccessObject;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface, LoginOutputBoundary loginOutputBoundary, GroupFactory groupFactory, GroupDataAccessInterface groupDataAccessInterface){
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.groupFactory = groupFactory;
        this.groupDataAccessObject = groupDataAccessInterface;
    }
    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String condition = loginInputData.getCondition();
        String location = loginInputData.getLocation();
        //
        System.out.println(loginInputData.getLocation());
        System.out.println(loginInputData.getGroupName());

        LoginOutputData loginOutputData;
        if (!userDataAccessObject.existsByName(username) && condition != "sign up"){
            loginPresenter.prepareFailView(username + ": Account does not exist");
        }else if(condition == "sign up"){
            if (!userDataAccessObject.existsByName(loginInputData.getKeyValue())){
                loginPresenter.prepareFailView("invalid userName");
            }else{
                User user = userDataAccessObject.get(loginInputData.getKeyValue());
                loginOutputData = new LoginOutputData(loginInputData.getKeyValue(),user.getLocation(),user.getGroupName(),false);
                loginPresenter.prepareSuccessView(loginOutputData);}
        else{
            if (!loginInputData.getGroupName().equals("")){
                if (loginInputData.getGroupCondition()) {
                    Group group = groupFactory.create(loginInputData.getGroupName(), new ArrayList<>(), LocalDateTime.now());
                    group.addMember(userDataAccessObject.get(loginInputData.getUsername()));
                    groupDataAccessObject.save(loginInputData.getGroupName(), group);
                } else {
                    groupDataAccessObject.addMember(loginInputData.getGroupName(), userDataAccessObject.get(loginInputData.getUsername()));
                    groupDataAccessObject.save(loginInputData.getGroupName(), null);
                }
            }
            loginOutputData = new LoginOutputData(username,location,loginInputData.getGroupName(),false);
            loginPresenter.prepareSuccessView(loginOutputData);
            User user = userDataAccessObject.get(loginInputData.getUsername());

            loginOutputData = new LoginOutputData(user.getUsername(), user.getLocation(),loginInputData.getGroupName(),false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }

    }
}

