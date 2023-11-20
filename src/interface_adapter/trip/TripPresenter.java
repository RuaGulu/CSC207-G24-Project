package interface_adapter.trip;

import interface_adapter.ViewManagerModel;
import use_case.trip.TripOutputBoundary;
import use_case.trip.TripOutputData;

public class TripPresenter implements TripOutputBoundary {
    private final TripViewModel tripViewModel;
    private final ViewManagerModel viewManagerModel;

    public TripPresenter(ViewManagerModel viewManagerModel, TripViewModel tripViewModel) {
        this.tripViewModel = tripViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(TripOutputData tripOutputData) {
        TripState tripState = tripViewModel.getState();
        tripState.setTripName(tripOutputData.getTripName());
        tripState.setTripId(tripOutputData.getTripId());
        tripViewModel.setState(tripState);
        tripViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(tripViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        TripState tripState = tripViewModel.getState();
        tripState.setError(error);
        tripViewModel.firePropertyChanged();
    }
}
