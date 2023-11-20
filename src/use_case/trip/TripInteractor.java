package use_case.trip;

import entity.CommonTrip;
import entity.Trip;
import java.util.Optional;

public class TripInteractor implements TripInputBoundary {
    private final TripUserDataAccessInterface tripDataAccessObject;
    private final TripOutputBoundary tripPresenter;

    public TripInteractor(TripUserDataAccessInterface tripDataAccessInterface, TripOutputBoundary tripOutputBoundary) {
        this.tripDataAccessObject = tripDataAccessInterface;
        this.tripPresenter = tripOutputBoundary;
    }

    @Override
    public void execute(TripInputData tripInputData) {
        Optional<Trip> tripOptional = tripDataAccessObject.findById(tripInputData.getTripId());
        if (!tripOptional.isPresent()) {
            tripPresenter.prepareFailView("Trip with ID " + tripInputData.getTripId() + " does not exist.");
        } else {
            Trip trip = tripOptional.get();
            TripOutputData tripOutputData = new TripOutputData(trip.getTripName(), trip.getTripId(), true);
            tripPresenter.prepareSuccessView(tripOutputData);
        }
    }
}
