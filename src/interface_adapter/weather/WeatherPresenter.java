package interface_adapter.weather;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.Weather.WeatherOutputBoundary;
import use_case.Weather.WeatherOutputData;

public class WeatherPresenter implements WeatherOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    private final WeatherViewModel weatherViewModel;

    public WeatherPresenter(ViewManagerModel viewManagerModel, WeatherViewModel weatherViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.weatherViewModel = weatherViewModel;

    }

    @Override
    public void prepareSuccessView(WeatherOutputData weather) {
        WeatherState state = weatherViewModel.getState();
        state.setWeather(weather.getWeather());
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setProperty("weather");
        this.weatherViewModel.setState(state);
        this.loggedInViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String error) {
        WeatherState weatherState = weatherViewModel.getState();
        weatherState.setLocationError(error);
        weatherViewModel.firePropertyChanged();

    }
}
