package use_case.create_group;

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

    public void execute(CreateGroupInputData groupInputData) {
        String username = groupInputData.getUser();
        String group_name = groupInputData.getGroupname();
        if (!getUser.existsByName(username)){
            CreateGroupPresenter.prepareFailView(username + ": User does not exist");
        }
        User user = getUser.get(username);
        if (groupDataAccessObject.existsByName(group_name)) {
            Group group = groupDataAccessObject.get(group_name);
            group.addmember(user);
            groupDataAccessObject.updateGroup(group_name, group);
            user.addgroup(group);
            getUser.updateuser(username, user);
        }
        else{
                LocalDateTime now = LocalDateTime.now();
                Group group = groupFactory.create(groupInputData.getGroupname(), user, now);
                groupDataAccessObject.save(group);
            }
    }
}
