package use_case.create_group;

import entity.User;
import entity.Group;


public class CreateGroupInteractor implements CreatGroupInputBoundary {
    final GroupDataAccessInterface groupDataAccessObject;

    final CreateGroupOutputBoundary CreateGroupPresenter;

    public CreateGroupInteractor(GroupDataAccessInterface groupDataAccessObject, CreateGroupOutputBoundary CreateGroupPresenter){
        this.groupDataAccessObject = groupDataAccessObject;
        this.CreateGroupPresenter = CreateGroupPresenter;
    }
    public void execute(CreateGroupInputData groupInputData) {
        String group_name = groupInputData.getGroupname();
        if (groupDataAccessObject.existsByName(group_name)){
            CreateGroupPresenter.prepareFailView(group_name + ": The group has already been created.");
        }else{
            User user = userDataAccessObject.get(loginInputData.getUsername());
            LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(),false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }

    }
}
