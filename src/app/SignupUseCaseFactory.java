package app;

import entity.CommonGroupFactory;
import entity.CommonUserFactory;
import entity.GroupFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.air_quality.AirQualityViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.weather.WeatherViewModel;
import use_case.group.GroupDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject, LoginUserDataAccessInterface loginuserDataAccessObject, LoggedInViewModel loggedInViewModel, WeatherViewModel weatherViewModel, AirQualityViewModel airQualityViewModel,GroupDataAccessInterface groupDataAccessInterface) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject,groupDataAccessInterface);
            LoginController loginController = createUserLoginUseCase(viewManagerModel,loggedInViewModel,loginViewModel,loginuserDataAccessObject, weatherViewModel, airQualityViewModel,groupDataAccessInterface);
            return new SignupView(signupController, signupViewModel, loginController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }
    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject, GroupDataAccessInterface groupDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        GroupFactory groupFactory = new CommonGroupFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory,groupFactory,groupDataAccessInterface);

        return new SignupController(userSignupInteractor);
    }
    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel, LoginUserDataAccessInterface loginUserDataAccessObject, WeatherViewModel weatherViewModel, AirQualityViewModel airQualityViewModel, GroupDataAccessInterface groupDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,loggedInViewModel,loginViewModel,weatherViewModel, airQualityViewModel);

        GroupFactory groupFactory = new CommonGroupFactory();

        LoginInputBoundary loginInputBoundary = new LoginInteractor(
                loginUserDataAccessObject, loginOutputBoundary,groupFactory,groupDataAccessInterface);

        return new LoginController(loginInputBoundary);
    }
}
