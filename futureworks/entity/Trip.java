package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface Trip {
    String getTripId();

    String getTripName();

    List<String> getUsers(); // Assuming a trip has multiple users, we use List<String>

    LocalDateTime getCreationTime();

    LocalDateTime getDepartureTime();

    LocalDateTime getReturnTime();
}
