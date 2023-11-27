package api;

import entity.Weather;

public interface WeatherDB {

    Weather getWeather(String location);

}