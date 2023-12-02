package app;

import api.WeatherDB;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.air_quality.AirQualityController;
import interface_adapter.logged_in.LoggedInViewModel;

import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherPresenter;
import interface_adapter.weather.WeatherViewModel;
import use_case.Weather.WeatherInputBoundary;
import use_case.Weather.WeatherInteractor;
import use_case.Weather.WeatherOutputBoundary;

import interface_adapter.air_quality.AirQualityPresenter;
import interface_adapter.air_quality.AirQualityPresenter;
import interface_adapter.air_quality.AirQualityViewModel;
import use_case.air_quality.AirQualityInputBoundary;
import use_case.air_quality.AirQualityInteractor;
import use_case.air_quality.AirQualityOutputBoundary;

import use_case.logged_in.*;
import view.LoggedinView;

import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {

    private LoggedInUseCaseFactory() {
    }

    public static LoggedinView create(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            WeatherViewModel weatherViewModel,
            AirQualityViewModel airQualityViewModel,
            LoggedInUserDataAccessInterface userDataAccessObject,
            WeatherDB weatherDataAccessObject) {
        try {
            WeatherController weatherController = createWeatherUseCase(viewManagerModel, weatherViewModel, loggedInViewModel, weatherDataAccessObject);
            AirQualityController airQualityController = createAirQualityUseCase(viewManagerModel, airQualityViewModel, loggedInViewModel, weatherDataAccessObject);
            return new LoggedinView(loggedInViewModel, weatherController, weatherViewModel, airQualityController, airQualityViewModel);
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
