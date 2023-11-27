package use_case.Weather;

import api.WeatherDB;
import entity.CommonUser;


public class WeatherInteractor implements WeatherInputBoundary{

    final WeatherDB weatherDataAccesObject;

    final WeatherOutputBoundary weatherPresenter;


    public WeatherInteractor(WeatherDB weatherDataAccesObject,
                             WeatherOutputBoundary weatherOutputBoundary) {
        this.weatherDataAccesObject = weatherDataAccesObject;
        this.weatherPresenter = weatherOutputBoundary;
    }

    @Override
    public void execute(WeatherInputData weatherInputData) {
        WeatherOutputData weatherOutputData = new WeatherOutputData(weatherDataAccesObject.getWeather(weatherInputData.getLocation()));
        weatherPresenter.prepareSuccessView(weatherOutputData);
    }
}