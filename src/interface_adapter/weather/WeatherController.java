package interface_adapter.weather;

import use_case.Weather.WeatherInputBoundary;
import use_case.Weather.WeatherInputData;

public class WeatherController {
    final WeatherInputBoundary weatherInputInteractor;
    public WeatherController(WeatherInputBoundary weatherInputInteractor) {
        this.weatherInputInteractor = weatherInputInteractor;
    }

    public void execute(String location) {
        WeatherInputData inputData = new WeatherInputData(location);

        weatherInputInteractor.execute(inputData);
    }
}