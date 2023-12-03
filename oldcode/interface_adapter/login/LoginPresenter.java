package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;


public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final WeatherViewModel weatherViewModel;

    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel,WeatherViewModel weatherViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.weatherViewModel = weatherViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        LoginState loginState = loginViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        loggedInState.setGroupName(response.getGroupName());
        loggedInState.setLocation(loginState.getLocation());


        //
        System.out.println("Arrived in login presenter");
        System.out.println(response.getUsername());
        System.out.println(loginState.getLocation());

//        WeatherState weatherState = weatherViewModel.getState();
//        weatherState.setLocation(response.getLocation());

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




