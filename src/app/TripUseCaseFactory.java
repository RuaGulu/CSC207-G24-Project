package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.trip.TripController;
import interface_adapter.trip.TripPresenter;
import interface_adapter.trip.TripViewModel;
import use_case.trip.TripInputBoundary;
import use_case.trip.TripInteractor;
import use_case.trip.TripOutputBoundary;
import use_case.trip.TripUserDataAccessInterface;
import view.TripView;

import javax.swing.*;

public class TripUseCaseFactory {
    private TripUseCaseFactory() {}

    public static TripView create(
            ViewManagerModel viewManagerModel,
            TripViewModel tripViewModel,
            TripUserDataAccessInterface tripUserDataAccessObject) {

        TripController tripController = createTripUseCase(viewManagerModel, tripViewModel, tripUserDataAccessObject);
        return new TripView(tripController, tripViewModel);
    }

    private static TripController createTripUseCase(
            ViewManagerModel viewManagerModel,
            TripViewModel tripViewModel,
            TripUserDataAccessInterface tripUserDataAccessObject) {

        TripOutputBoundary tripOutputBoundary = new TripPresenter(viewManagerModel, tripViewModel);

        TripInputBoundary tripInteractor = new TripInteractor(
                tripUserDataAccessObject, tripOutputBoundary);

        return new TripController(tripInteractor);
    }
}
