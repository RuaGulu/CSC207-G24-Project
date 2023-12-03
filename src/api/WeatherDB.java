package api;

import entity.Weather;
import entity.AirQuality;

public interface WeatherDB {

    Weather getWeather(String location);

    AirQuality getAirQuality(String location);

}