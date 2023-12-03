package app;

import api.WeatherDB;
import entity.CommonGroupFactory;
import entity.CommonUserFactory;
import entity.GroupFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.air_quality.AirQualityController;
import interface_adapter.air_quality.AirQualityPresenter;
import interface_adapter.air_quality.AirQualityViewModel;
import interface_adapter.group.GroupController;
import interface_adapter.group.GroupPresenter;
import interface_adapter.group.GroupViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherPresenter;
import interface_adapter.weather.WeatherViewModel;
import use_case.Weather.WeatherInputBoundary;
import use_case.Weather.WeatherInteractor;
import use_case.Weather.WeatherOutputBoundary;
import use_case.air_quality.AirQualityInputBoundary;
import use_case.air_quality.AirQualityInteractor;
import use_case.air_quality.AirQualityOutputBoundary;
import use_case.group.GroupDataAccessInterface;
import use_case.group.GroupInputBoundary;
import use_case.group.GroupInteractor;
import use_case.group.GroupOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoggedinView;

import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {

    private LoggedInUseCaseFactory() {}

    public static LoggedinView create(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            WeatherViewModel weatherViewModel,
            AirQualityViewModel airQualityViewModel,
            LoginUserDataAccessInterface loginUserDataAccessInterface,
            WeatherDB weatherDataAccessObject,
            GroupViewModel groupViewModel,
            GroupDataAccessInterface groupDataAccessInterface) {
        try {
            WeatherController weatherController = createWeatherUseCase(viewManagerModel, weatherViewModel, loggedInViewModel, weatherDataAccessObject);
            GroupController groopController = createGroupUseCase(viewManagerModel,groupViewModel,loggedInViewModel,groupDataAccessInterface,weatherDataAccessObject,loginUserDataAccessInterface);
            AirQualityController airQualityController = createAirQualityUseCase(viewManagerModel, airQualityViewModel, loggedInViewModel, weatherDataAccessObject);
            return new LoggedinView(loggedInViewModel, weatherController, weatherViewModel,airQualityController,airQualityViewModel,groupViewModel,groopController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file");
        }

        return null;
    }

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

    private static GroupController createGroupUseCase(
            ViewManagerModel viewManagerModel,
            GroupViewModel groupViewModel,
            LoggedInViewModel loggedInViewModel,
            GroupDataAccessInterface groupDataAccessObject,
            WeatherDB weatherDataAccessObject,
            LoginUserDataAccessInterface loginUserDataAccessInterface) throws IOException{
        GroupOutputBoundary groupOutputBoundary = new GroupPresenter(viewManagerModel,groupViewModel, loggedInViewModel);
        GroupFactory groupFactory = new CommonGroupFactory();
        GroupInputBoundary groupInteractor = new GroupInteractor(groupDataAccessObject,groupOutputBoundary,groupFactory,weatherDataAccessObject,loginUserDataAccessInterface);
        return new GroupController(groupInteractor);
    }

    private static AirQualityController createAirQualityUseCase(
            ViewManagerModel viewManagerModel,
            AirQualityViewModel airQualityViewModel,
            LoggedInViewModel loggedInViewModel,
            WeatherDB weatherDataAccessObject) throws IOException {

        AirQualityOutputBoundary airQualityOutputBoundary = new AirQualityPresenter(viewManagerModel, airQualityViewModel, loggedInViewModel);

        UserFactory userFactory = new CommonUserFactory();

        AirQualityInputBoundary airQualityInteractor = new AirQualityInteractor(weatherDataAccessObject, airQualityOutputBoundary);

        return new AirQualityController(airQualityInteractor);
    }

}
