package use_case;

import api.WeatherDB;
import entity.Weather;

public final class GetWeatherUseCase {
    private final WeatherDB weatherDB;

    public GetWeatherUseCase(WeatherDB weatherDB){
        this.weatherDB = weatherDB;
    }
    public Weather getWeather(String location){
        return weatherDB.getWeather(location);
    }

}
