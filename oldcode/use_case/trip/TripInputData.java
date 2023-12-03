package use_case.trip;

public class TripInputData {
    private final String tripId;

    public TripInputData(String tripId) {
        this.tripId = tripId;
    }

    public String getTripId() {
        return tripId;
    }
}
