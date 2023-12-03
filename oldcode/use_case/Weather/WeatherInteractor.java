package use_case.Weather;

import api.WeatherDB;


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
        //
        System.out.println(weatherInputData.getLocation());

        WeatherOutputData weatherOutputData = new WeatherOutputData(weatherDataAccesObject.getWeather(weatherInputData.getLocation()));
        weatherPresenter.prepareSuccessView(weatherOutputData);
    }
}
