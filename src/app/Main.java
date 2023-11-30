package app;

import data_access.FilerGroupDataAccessObject;
import data_access.FilerUserDataAccessObject;
import entity.CommonGroupFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_group.CreateGroupViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_usergroup.SearchUserGroupViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.weather.WeatherViewModel;
import view.GroupView;
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
        CreateGroupViewModel createGroupViewModel = new CreateGroupViewModel();
        SearchUserGroupViewModel searchUserGroupViewModel = new SearchUserGroupViewModel();
        WeatherViewModel weatherViewModel = new WeatherViewModel();

        FilerUserDataAccessObject userDataAccessObject;
        FilerUserDataAccessObject loginUserDataAccessObject;
        FilerUserDataAccessObject loggedInUserDataAccessObject;
        FilerUserDataAccessObject groupFindGroupDataAccessObject;
        FilerUserDataAccessObject searchGroupUserDataAccessObject;
        FilerGroupDataAccessObject groupDataAccessObject;

        try {
            userDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory());
            loginUserDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory());
            loggedInUserDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory());
            groupFindGroupDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory());
            searchGroupUserDataAccessObject = new FilerUserDataAccessObject("./users.csv", new CommonUserFactory());
            groupDataAccessObject = new FilerGroupDataAccessObject("./groups.csv", new CommonGroupFactory());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, loginUserDataAccessObject, loggedinViewModel );
        views.add(signupView,signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel,loginViewModel,loggedinViewModel,loginUserDataAccessObject);
        views.add(loginView,loginView.viewName);

        LoggedinView loggedinView = LoggedInUseCaseFactory.create(viewManagerModel,loggedinViewModel, weatherViewModel, loggedInUserDataAccessObject);

        GroupView groupView = GroupUseCaseFactory.create(viewManagerModel,createGroupViewModel,searchUserGroupViewModel, groupFindGroupDataAccessObject, groupDataAccessObject, searchGroupUserDataAccessObject);
        views.add(groupView,groupView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setLocation(675,386);
        application.pack();
        application.setVisible(true);


    }
}
