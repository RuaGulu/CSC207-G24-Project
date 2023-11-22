package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;


public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final WeatherViewModel weatherViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, WeatherViewModel weatherViewModel, LoginViewModel loginViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.weatherViewModel = weatherViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        WeatherState weatherState = weatherViewModel.getState();
        weatherState.setLocation(response.getLocation());
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}



