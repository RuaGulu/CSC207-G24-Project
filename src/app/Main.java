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
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.weather.WeatherViewModel;
import view.LoggedinView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("system");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedinViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        WeatherViewModel weatherViewModel = new WeatherViewModel();
        AirQualityViewModel airQualityViewModel = new AirQualityViewModel();
        GroupViewModel groupViewModel = new GroupViewModel();

        FilerUserDataAccessObject userDataAccessObject;
        FilerUserDataAccessObject loginUserDataAccessObject;
        FilerUserDataAccessObject loggedInUserDataAccessObject;
        GroupDataAccessObject groupDataAccessObject;
        WeatherDB weatherDataAccessObject;

        try {
            userDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory(), new CommonGroupFactory());
            loginUserDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory(),new CommonGroupFactory());
            loggedInUserDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory(),new CommonGroupFactory());
            groupDataAccessObject = new GroupDataAccessObject("./groups.csv",new CommonGroupFactory(),new CommonUserFactory());
            weatherDataAccessObject = new APIDataAccessObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, loginUserDataAccessObject, loggedinViewModel,weatherViewModel,airQualityViewModel,groupDataAccessObject );
        views.add(signupView,signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel,loginViewModel,loggedinViewModel,loginUserDataAccessObject,weatherViewModel, airQualityViewModel,groupDataAccessObject);
        views.add(loginView,loginView.viewName);

        LoggedinView loggedinView = LoggedInUseCaseFactory.create(viewManagerModel,loggedinViewModel,weatherViewModel,airQualityViewModel,loggedInUserDataAccessObject,weatherDataAccessObject,groupViewModel,groupDataAccessObject);
        views.add(loggedinView,loggedinView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setPreferredSize(new Dimension(500,400));
        application.setLocation(636,333);
        application.pack();
        application.setVisible(true);


    }
}
