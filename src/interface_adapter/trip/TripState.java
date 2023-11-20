package interface_adapter.trip;

public class TripState {

    private String tripName = "";
    private String tripId = "";
    private String error = "";

    public TripState(TripState copy) {
        tripName = copy.tripName;
        tripId = copy.tripId;
        error = copy.error;
    }

    public TripState() {
    }

    public String getTripName() {
        return tripName;
    }

    public String getTripId() {
        return tripId;
    }

    public String getError() {
        return error;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "TripState{" +
                "tripName='" + tripName + '\'' +
                ", tripId='" + tripId + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
