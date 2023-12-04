package app;

import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.air_quality.AirQualityViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.weather.WeatherViewModel;
import use_case.group.GroupDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            WeatherViewModel weatherViewModel,
            GroupDataAccessInterface groupDataAccessObject,
            AirQualityViewModel airQualityViewModel) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject,weatherViewModel,groupDataAccessObject,airQualityViewModel);
            return new LoginView(loginController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }
    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            WeatherViewModel weatherViewModel,
            GroupDataAccessInterface groupDataAccessObject,
            AirQualityViewModel airQualityViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel,weatherViewModel,airQualityViewModel);

        GroupFactory groupFactory = new CommonGroupFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary,groupFactory,groupDataAccessObject);

        return new LoginController(loginInteractor);
    }
}
