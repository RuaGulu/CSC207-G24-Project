package use_case.trip;

import entity.Trip;

import java.util.Optional;

public interface TripUserDataAccessInterface {
    Optional<Trip> findById(String tripId);
    void save(Trip trip);
}
