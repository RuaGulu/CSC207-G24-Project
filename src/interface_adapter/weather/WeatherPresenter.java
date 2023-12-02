package interface_adapter.weather;

import interface_adapter.ViewManagerModel;
import interface_adapter.air_quality.AirQualityState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.Weather.WeatherOutputBoundary;
import use_case.Weather.WeatherOutputData;

public class WeatherPresenter implements WeatherOutputBoundary {
    private final WeatherViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public WeatherPresenter(ViewManagerModel viewManagerModel, WeatherViewModel weatherViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = weatherViewModel;
    }

    @Override
    public void prepareSuccessView(WeatherOutputData weather) {
        WeatherState state = viewModel.getState();
        state.setWeather(weather.getWeather());
        this.viewModel.setState(state);
        this.viewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String error) {
        WeatherState weatherState = viewModel.getState();
        weatherState.setLocationError(error);
        viewModel.firePropertyChanged();

    }
}
