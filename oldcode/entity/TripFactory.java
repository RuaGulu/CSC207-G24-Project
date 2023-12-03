package entity;

public interface TripFactory {
    Trip create(String tripId, String tripName);
}
