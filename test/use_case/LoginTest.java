package use_case;

import api.APIDataAccessObject;
import data_access.FilerUserDataAccessObject;
import data_access.GroupDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.air_quality.AirQualityViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import interface_adapter.weather.WeatherViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.group.GroupDataAccessInterface;
import use_case.login.LoginInputData;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;


import java.io.IOException;

public class LoginTest {

    private LoginUserDataAccessInterface userDataAccessObject;
    private LoginOutputBoundary loginPresenter;

    private LoginInteractor loginInteractor;
    private UserFactory userFactory;
    private GroupFactory groupFactory;
    private final String user1 = "User7";
    private final String user2 = "User8";
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private WeatherViewModel weatherViewModel;
    private GroupDataAccessInterface groupDataAccessObject;
    private LoginState state = new LoginState();
    private AirQualityViewModel airQualityViewModel;

    @Before
    public void init(){
        try {
            userDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory(), new CommonGroupFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loginViewModel = new LoginViewModel();

        viewManagerModel = new ViewManagerModel();

        loggedInViewModel = new LoggedInViewModel();

        weatherViewModel = new WeatherViewModel();

        airQualityViewModel = new AirQualityViewModel();

        loginPresenter = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, weatherViewModel, airQualityViewModel);

        userFactory = new CommonUserFactory();

        try {
            groupDataAccessObject = new GroupDataAccessObject("./groups.csv", new CommonGroupFactory(), new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        groupFactory = new CommonGroupFactory();
        loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter, groupFactory, groupDataAccessObject);
        CommonUser existingUser = new CommonUser(user1, "Toronto", "Toronto", "testgroup2");
        userDataAccessObject.save(existingUser);
    }
    @Test
    public void validInput(){
        LoginInputData loginInputData = new LoginInputData(user1,"Toronto", "", "log in", false);
        loginInteractor.execute(loginInputData);
        assert(loggedInViewModel.getState().getUsername().equals(user1));
    }
    @Test
    public void accountnotexist(){
        LoginInputData loginInputData = new LoginInputData(user2, "Toronto", "inputgroup", "sign up", false);
        loginInteractor.execute(loginInputData);
        assert(loggedInViewModel.getState().getUsername().equals(user2));
    }
    @Test
    public void invalidInput(){
        LoginInputData loginInputData = new LoginInputData(user2, "Toronto", "inputgroup", "log in", false);
        loginInteractor.execute(loginInputData);
        assert(loginViewModel.getState().getUsernameError().equals("User8: Account does not exist"));
    }
}
