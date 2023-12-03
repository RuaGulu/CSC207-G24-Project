package use_case.trip;

public class TripOutputData {
    private final String tripName;
    private final String tripId;
    private final boolean success;

    public TripOutputData(String tripName, String tripId, boolean success) {
        this.tripName = tripName;
        this.tripId = tripId;
        this.success = success;
    }

    public String getTripName() {
        return tripName;
    }

    public String getTripId() {
        return tripId;
    }

    public boolean isSuccess() {
        return success;
    }
}
