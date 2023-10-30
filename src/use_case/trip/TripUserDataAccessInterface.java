package use_case.trip;

import entity.Trip;

public interface TripUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Trip trip);
}
