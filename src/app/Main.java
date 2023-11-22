package app;

import data_access.FilerUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
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

        FilerUserDataAccessObject userDataAccessObject;
        FilerUserDataAccessObject loginUserDataAccessObject;
        FilerUserDataAccessObject loggedInUserDataAccessObject;

        try {
            userDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory());
            loginUserDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory());
            loggedInUserDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, loginUserDataAccessObject, loggedinViewModel );
        views.add(signupView,signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel,loginViewModel,loggedinViewModel,loginUserDataAccessObject);
        views.add(loginView,loginView.viewName);

        LoggedinView loggedinView = LoggedInUseCaseFactory.create(viewManagerModel,loggedinViewModel, weatherViewModel, loggedInUserDataAccessObject);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setLocation(675,386);
        application.pack();
        application.setVisible(true);


    }
}
