package use_case;

import api.APIDataAccessObject;
import api.WeatherDB;
import entity.Weather;
import org.junit.Before;
import org.junit.Test;

public class GetWeatherUseCaseTest {
    private WeatherDB weatherDB;
    private GetWeatherUseCase getWeatherUseCase;
    @Before
    public void init(){
        weatherDB = new APIDataAccessObject();
        getWeatherUseCase = new GetWeatherUseCase(weatherDB);
    }
    @Test
    public void testGetWeather(){
        Weather toronto = getWeatherUseCase.getWeather("Toronto");
        assert (toronto.getLocation().equals("Toronto"));
    }
}
