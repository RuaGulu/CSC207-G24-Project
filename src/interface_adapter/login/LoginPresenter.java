package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.air_quality.AirQualityViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import interface_adapter.weather.WeatherState;
import interface_adapter.air_quality.AirQualityState;


public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final WeatherViewModel weatherViewModel;

    private final AirQualityViewModel airQualityViewModel;

    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel,WeatherViewModel weatherViewModel, AirQualityViewModel airQualityViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.weatherViewModel = weatherViewModel;
        this.airQualityViewModel = airQualityViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        LoginState loginState = loginViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        loggedInState.setGroupName(response.getGroupName());
        loggedInState.setLocation(loginState.getLocation());


        //
        System.out.println("到达login presenter");
        System.out.println(response.getUsername());
        System.out.println(loginState.getLocation());

        WeatherState weatherState = weatherViewModel.getState();
        weatherState.setLocation(response.getLocation());

        AirQualityState airQualityState = airQualityViewModel.getState();
        airQualityState.setLocation(response.getLocation());

        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();
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




