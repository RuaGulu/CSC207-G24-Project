package use_case;

import api.APIDataAccessObject;
import api.WeatherDB;
import entity.CommonGroup;
import entity.CommonUser;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherPresenter;
import interface_adapter.weather.WeatherViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.Weather.WeatherInputData;
import use_case.Weather.WeatherInteractor;
import use_case.Weather.WeatherOutputBoundary;
import use_case.Weather.WeatherOutputData;

import java.time.LocalDateTime;

public class WeatherTest {
    private WeatherInputData weatherInputData;

    private WeatherViewModel weatherViewModel;
    private WeatherOutputBoundary weatherOutputBoundary;
    private WeatherDB weatherDB;
    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;
    private WeatherInteractor weatherInteractor;

    @Before
    public void init(){

        viewManagerModel = new ViewManagerModel();

        loggedInViewModel = new LoggedInViewModel();

        weatherViewModel = new WeatherViewModel();

        weatherDB = new APIDataAccessObject();

        weatherOutputBoundary = new WeatherPresenter(viewManagerModel, weatherViewModel, loggedInViewModel);

        weatherInteractor = new WeatherInteractor(weatherDB, weatherOutputBoundary);
    }
    @Test
    public void testToronto(){
        weatherInputData = new WeatherInputData("Toronto");

        weatherInteractor.execute(weatherInputData);

        assert (loggedInViewModel.getState().getProperty().equals("weather"));
    }
}
