package use_case.Weather;

import api.WeatherDB;
import entity.CommonUser;


public class WeatherInteractor implements WeatherInputBoundary{

    final WeatherDB weatherDataAccesObject;

    final WeatherOutputBoundary weatherPresenter;

    final CommonUser user;

    public WeatherInteractor(WeatherDB weatherDataAccesObject,
                             WeatherOutputBoundary weatherOutputBoundary,
                             CommonUser user) {
        this.weatherDataAccesObject = weatherDataAccesObject;
        this.weatherPresenter = weatherOutputBoundary;
        this.user = user;
    }

    @Override
    public void execute(WeatherInputData weatherInputData) {
        WeatherOutputData weatherOutputData = new WeatherOutputData(weatherDataAccesObject.getWeather(user.getLocation()));
        weatherPresenter.prepareSuccessView(weatherOutputData);
    }
}
