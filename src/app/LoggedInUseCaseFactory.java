package app;

import api.WeatherDB;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherPresenter;
import interface_adapter.weather.WeatherViewModel;
import use_case.Weather.WeatherInputBoundary;
import use_case.Weather.WeatherInteractor;
import use_case.Weather.WeatherOutputBoundary;
import use_case.logged_in.*;
import view.LoggedinView;

import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {

    private LoggedInUseCaseFactory() {}

    public static LoggedinView create(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            WeatherViewModel weatherViewModel,
            LoggedInUserDataAccessinterface userDataAccessObject,
            WeatherDB weatherDataAccessObject) {
        try {
            WeatherController weatherController = createWeatherUseCase(viewManagerModel, weatherViewModel, loggedInViewModel, weatherDataAccessObject);
            return new LoggedinView(loggedInViewModel, weatherController, weatherViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file");
        }

        return null;
    }

//    private static LoggedInController createLoggedInUseCase(
//            ViewManagerModel viewManagerModel,
//            LoggedInViewModel loggedInViewModel,
//            LoggedInUserDataAccessinterface userDataAccessObject) throws IOException {
//
//        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(viewManagerModel, loggedInViewModel);
//
//        UserFactory userFactory = new CommonUserFactory();
//
//        LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(userDataAccessObject, loggedInOutputBoundary);
//
//        return new LoggedInController(loggedInInteractor);
//    }

    private static WeatherController createWeatherUseCase(
            ViewManagerModel viewManagerModel,
            WeatherViewModel weatherViewModel,
            LoggedInViewModel loggedInViewModel,
            WeatherDB weatherDataAccessObject) throws IOException {

        WeatherOutputBoundary weatherOutputBoundary = new WeatherPresenter(viewManagerModel, weatherViewModel, loggedInViewModel);

        UserFactory userFactory = new CommonUserFactory();

        WeatherInputBoundary weatherInteractor = new WeatherInteractor(weatherDataAccessObject, weatherOutputBoundary);

        return new WeatherController(weatherInteractor);
    }

//    private static GroupController createGroupUseCase(
//            ViewManagerModel viewManagerModel,
//            GroupViewModel groupViewModel,
//            LoggedInViewModel loggedInViewModel,
//            GroupDataAccessInterface groupDataAccessObject) throws IOException{
//        GroupOutputBoundary groupOutputBoundary = new GroupPresenter(viewManagerModel,groupViewModel);
//        GroupFactory groupFactory = new CommonGroupFactory();
//        GroupInputBoundary groupInteractor = new GroupInteractor(groupDataAccessObject,groupOutputBoundary,groupFactory)
//    }
//    )
}
