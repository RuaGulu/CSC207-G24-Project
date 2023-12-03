package use_case;

import api.APIDataAccessObject;
import api.WeatherDB;
import interface_adapter.ViewManagerModel;
import interface_adapter.air_quality.AirQualityPresenter;
import interface_adapter.air_quality.AirQualityViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherPresenter;
import interface_adapter.weather.WeatherViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.Weather.WeatherInteractor;
import use_case.air_quality.AirQualityInputData;
import use_case.air_quality.AirQualityInteractor;
import use_case.air_quality.AirQualityOutputBoundary;

public class AirQualityTest {
    private WeatherDB airDataAccessObject;
    private AirQualityOutputBoundary airQualityPresenter;
    private AirQualityViewModel airQualityViewModel;
    private LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private AirQualityInteractor airQualityInteractor;

    @Before
    public void init(){

        viewManagerModel = new ViewManagerModel();

        loggedInViewModel = new LoggedInViewModel();

        airQualityViewModel = new AirQualityViewModel();

        airDataAccessObject = new APIDataAccessObject();

        airQualityPresenter = new AirQualityPresenter(viewManagerModel, airQualityViewModel, loggedInViewModel);

        airQualityInteractor = new AirQualityInteractor(airDataAccessObject, airQualityPresenter);
    }

    @Test
    public void testFunction(){
        AirQualityInputData airQualityInputData = new AirQualityInputData("Toronto");
        airQualityInteractor.execute(airQualityInputData);

        assert (loggedInViewModel.getState().getProperty().equals("airquality"));
    }
}
