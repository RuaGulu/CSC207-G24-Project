package use_case.Weather;

import use_case.Weather.WeatherInputData;

public interface WeatherInputBoundary {

    void execute(WeatherInputData weatherInputData);
}
