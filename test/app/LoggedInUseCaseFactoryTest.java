package app;

import api.APIDataAccessObject;
import api.WeatherDB;
import data_access.FilerUserDataAccessObject;
import data_access.GroupDataAccessObject;
import entity.CommonGroupFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.air_quality.AirQualityViewModel;
import interface_adapter.group.GroupViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.weather.WeatherViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.group.GroupDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import java.io.IOException;

import static org.junit.Assert.*;

public class LoggedInUseCaseFactoryTest {
    private GroupDataAccessInterface groupDataAccessInterface;
    private WeatherDB weatherDataAccessObject;
    private LoginUserDataAccessInterface loginUserDataAccessInterface;
    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;
    private WeatherViewModel weatherViewModel;
    private AirQualityViewModel airQualityViewModel;
    private GroupViewModel groupViewModel;
    private LoginViewModel loginViewModel;

    @Before
    public void init() {
        viewManagerModel = new ViewManagerModel();
        loggedInViewModel = new LoggedInViewModel();
        weatherViewModel = new WeatherViewModel();
        airQualityViewModel = new AirQualityViewModel();
        groupViewModel = new GroupViewModel();
        loginViewModel = new LoginViewModel();

        try {
            groupDataAccessInterface = new GroupDataAccessObject("./groups.csv", new CommonGroupFactory(), new CommonUserFactory());
            loginUserDataAccessInterface = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory(), new CommonGroupFactory());
            weatherDataAccessObject = new APIDataAccessObject();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void loginViewCreateTest(){
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel,loginViewModel,loggedInViewModel,loginUserDataAccessInterface,weatherViewModel,groupDataAccessInterface,airQualityViewModel);
        assertTrue("Test completed successfully", true);
    }
}
