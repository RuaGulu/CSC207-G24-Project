package interface_adapter.trip;

import use_case.trip.TripInputBoundary;
import use_case.trip.TripInputData;

public class TripController {
    private final TripInputBoundary tripUseCaseInteractor;

    public TripController(TripInputBoundary tripUseCaseInteractor) {
        this.tripUseCaseInteractor = tripUseCaseInteractor;
    }

    public void createTrip(String tripId) {
        TripInputData tripInputData = new TripInputData(tripId);
        tripUseCaseInteractor.execute(tripInputData);
    }
}
