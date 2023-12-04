package use_case;

import api.WeatherDB;
import org.junit.FixMethodOrder;
import api.APIDataAccessObject;
import data_access.FilerUserDataAccessObject;
import data_access.GroupDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import use_case.group.GroupDataAccessInterface;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;
import java.util.List;

public class SignupTest {
    private SignupUserDataAccessInterface userDataAccessObject;
    private GroupDataAccessInterface groupDataAccessObject;
    private SignupOutputBoundary userPresenter;

    private SignupInteractor signupInteractor;

    private UserFactory userFactory;
    private GroupFactory groupFactory;
    private final String user1 = "Usersignuptest";
    private final String user2 = "User6";
    private final String user3 = "Userno";
    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private WeatherDB weatherDB;
    private SignupState state = new SignupState();

    @Before
    public void init(){
        try {
            groupDataAccessObject = new GroupDataAccessObject("./groups.csv", new CommonGroupFactory(), new CommonUserFactory());
            userDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory(), new CommonGroupFactory());
            weatherDB = new APIDataAccessObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        signupViewModel = new SignupViewModel();

        loginViewModel = new LoginViewModel();

        viewManagerModel = new ViewManagerModel();

        userPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        groupFactory = new CommonGroupFactory();
        userFactory = new CommonUserFactory();
        signupInteractor = new SignupInteractor(userDataAccessObject, userPresenter, userFactory,groupFactory, groupDataAccessObject, weatherDB);
        CommonUser existingUser = new CommonUser(user2, "Toronto", "Italy", "usergroup");
        userDataAccessObject.save(existingUser);
    }
    @Test
    public void validInputWithOldGroup(){
        SignupInputData signupInputData = new SignupInputData(user1, "Toronto", "usersignuptestgroup", false);
        signupInteractor.execute(signupInputData);

        assert(userDataAccessObject.existsByName(user1));
        assert(groupDataAccessObject.existsByName("usersignuptestgroup"));
        assert(loginViewModel.getState().getGroup().equals("usersignuptestgroup"));
    }
    @Test
    public void validInputWithNewGroup(){
        SignupInputData signupInputData = new SignupInputData("newuser", "Toronto", "newgroup", true);
        signupInteractor.execute(signupInputData);

        assert(userDataAccessObject.existsByName(user1));
        assert(groupDataAccessObject.existsByName("newgroup"));
        assert(loginViewModel.getState().getGroup().equals("newgroup"));
    }
    @Test
    public void errorLocation(){
        SignupInputData signupInputData = new SignupInputData(user3, "aaaa", "user6group", true);
        signupInteractor.execute(signupInputData);
        assert(signupViewModel.getState().getUsernameError().equals("invalid location"));
    }
    @Test
    public void existsByName(){
        SignupInputData signupInputData = new SignupInputData(user2, "Toronto", "user6group", true);
        signupInteractor.execute(signupInputData);
        assert(signupViewModel.getState().getUsernameError().equals("User already exists"));
    }

    @Test
      public void groupExists(){
        SignupInputData signupInputData = new SignupInputData(user3, "Toronto", "usersignuptestgroup", true);
        signupInteractor.execute(signupInputData);
        System.out.println(signupViewModel.getState().getUsernameError());
        assert(signupViewModel.getState().getUsernameError().equals("Group already exists"));
    }

    @Test
    public void cantFindGroup(){
        SignupInputData signupInputData = new SignupInputData(user3, "Toronto", "usergroup", false);
        signupInteractor.execute(signupInputData);
        assert(signupViewModel.getState().getUsernameError().equals("Group does not exist"));
    }
}