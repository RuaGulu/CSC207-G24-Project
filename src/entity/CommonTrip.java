package entity;

        import java.time.LocalDateTime;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.UUID;

public class CommonTrip implements Trip {
    private final String tripId;
    private final String tripName;
    private final List<String> users;
    private final LocalDateTime creationTime;
    private final LocalDateTime departureTime;
    private final LocalDateTime returnTime;
    private final List<City> cities; // Assuming you have a City class

    public CommonTrip(String tripName, LocalDateTime departureTime, LocalDateTime returnTime, List<City> cities) {
        this.tripId = UUID.randomUUID().toString(); // Generate a unique ID for the trip
        this.tripName = tripName;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.cities = cities;
        this.creationTime = LocalDateTime.now(); // Set the creation time to now
        this.users = new ArrayList<>(); // Initialize the users list
    }

    @Override
    public String getTripId() {
        return tripId;
    }

    @Override
    public String getTripName() {
        return tripName;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    @Override
    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    @Override
    public List<String> getUsers() {
        return users;
    }
}

