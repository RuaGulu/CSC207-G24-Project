package interface_adapter.air_quality;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.air_quality.AirQualityOutputBoundary;
import use_case.air_quality.AirQualityOutputData;

public class AirQualityPresenter implements AirQualityOutputBoundary {
    private final AirQualityViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public AirQualityPresenter(ViewManagerModel viewManagerModel, AirQualityViewModel airQualityViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = airQualityViewModel;
        this.loggedInViewModel = loggedInViewModel;

    }

    @Override
    public void prepareSuccessView(AirQualityOutputData airQuality) {
        AirQualityState state = viewModel.getState();
        state.setAirQuality(airQuality.getAirQuality());
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setProperty("airquality");
        this.viewModel.setState(state);
        this.viewModel.firePropertyChanged();
        this.loggedInViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        AirQualityState airQualityState = viewModel.getState();
        airQualityState.setLocationError(error);
        viewModel.firePropertyChanged();
    }
}