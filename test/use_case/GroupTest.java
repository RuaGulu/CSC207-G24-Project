package use_case;

import api.APIDataAccessObject;
import api.WeatherDB;
import data_access.GroupDataAccessObject;
import data_access.FilerUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;

import interface_adapter.group.GroupPresenter;
import interface_adapter.group.GroupState;
import interface_adapter.group.GroupViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import use_case.group.GroupDataAccessInterface;
import use_case.group.GroupInputData;
import use_case.group.GroupInteractor;
import use_case.group.GroupOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;

public class GroupTest {
    private GroupDataAccessInterface groupDataAccessObject;

    private GroupOutputBoundary groupPresenter;

    private GroupInteractor groupInteractor;

    private GroupFactory groupFactory;

    private SignupUserDataAccessInterface getUser;

    private LoginUserDataAccessInterface loginUserDataAccessInterface;

    private WeatherDB weatherDataAccessObject;

    private final String user1 = "User3";
    private final String user2 = "User4";
    private final String group1 = "Group2";
    private ViewManagerModel viewManagerModel;
    private GroupViewModel groupViewModel;
    private LoggedInViewModel loggedInViewModel;
    private GroupState state = new GroupState();

    private CommonUser testuser;

    @Before
    public void init() {
        try {
            groupDataAccessObject = new GroupDataAccessObject("./groups.csv", new CommonGroupFactory(), new CommonUserFactory());
            getUser = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory(), new CommonGroupFactory());
            loginUserDataAccessInterface = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory(), new CommonGroupFactory());
            weatherDataAccessObject = new APIDataAccessObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        groupViewModel = new GroupViewModel();

        viewManagerModel = new ViewManagerModel();

        loggedInViewModel = new LoggedInViewModel();

        groupPresenter = new GroupPresenter(viewManagerModel, groupViewModel, loggedInViewModel);

        testuser = new CommonUser(user2, "Toronto", "Toronto", "usersignuptestgroup");
        getUser.save(new CommonUser(user1, "London", "Toronto", "usersignuptestgroup"));
        getUser.save(testuser);

        groupFactory = new CommonGroupFactory();
        groupInteractor = new GroupInteractor(groupDataAccessObject, groupPresenter, groupFactory, weatherDataAccessObject, loginUserDataAccessInterface);
    }

    @Test
    public void addUserTwice() {
        GroupInputData groupInputData = new GroupInputData(user1, "Toronto");
        groupInteractor.execute(groupInputData);
        groupInteractor.execute(groupInputData);
        assert (getUser.existsByName(user1));
        assert (loggedInViewModel.getState().getProperty().equals("group"));
    }

    @Test
    public void addAnotherUserToTheGroup() {
        GroupInputData groupInputData = new GroupInputData(user2, "Toronto");
        groupInteractor.execute(groupInputData);
        //assert (testuser.getGroupName().equals(group1));
        //assert (groupDataAccessObject.existsByName(group1));
        assert (loggedInViewModel.getState().getProperty().equals("group"));
    }
}

