package use_case.create_group;

import data_access.FilerUserDataAccessObject;
import entity.*;
import use_case.login.LoginUserDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;


public class CreateGroupInteractor implements CreatGroupInputBoundary {
    final GroupDataAccessInterface groupDataAccessObject;

    final CreateGroupOutputBoundary CreateGroupPresenter;

    final GroupFactory groupFactory;

    final LoginUserDataAccessInterface getUser;

    public CreateGroupInteractor(GroupDataAccessInterface groupDataAccessObject, CreateGroupOutputBoundary CreateGroupPresenter, GroupFactory groupFactory, LoginUserDataAccessInterface getUser) {
        this.groupDataAccessObject = groupDataAccessObject;
        this.CreateGroupPresenter = CreateGroupPresenter;
        this.groupFactory = groupFactory;
        this.getUser = getUser;
    }

    public void execute(CreateGroupInputData groupInputData, String username) {
        String group_name = groupInputData.getGroupname();
        if (groupDataAccessObject.existsByName(group_name)) {
            CreateGroupPresenter.prepareFailView(group_name + ": The group has already been created.");
        } else {
            if (!getUser.existsByName(username)){
                CreateGroupPresenter.prepareFailView(username + ": User does not exist");
            }
            else{
                User user = getUser.get(username);
                LocalDateTime now = LocalDateTime.now();
                Group group = groupFactory.create(groupInputData.getGroupname(), user, now);
                groupDataAccessObject.save(group);
            }
        }
    }
}
