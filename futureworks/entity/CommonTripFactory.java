package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommonTripFactory implements TripFactory {

    @Override
    public Trip create(String tripId, String tripName) {
        return new Trip() {
            private final String id = tripId;
            private final String name = tripName;
            private final List<String> users = new ArrayList<>();
            private final LocalDateTime creationTime = LocalDateTime.now();
            private LocalDateTime departureTime;
            private LocalDateTime returnTime;

            @Override
            public String getTripId() {
                return id;
            }

            @Override
            public String getTripName() {
                return name;
            }

            @Override
            public List<String> getUsers() {
                return users;
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

            public void setDepartureTime(LocalDateTime departureTime) {
                this.departureTime = departureTime;
            }

            public void setReturnTime(LocalDateTime returnTime) {
                this.returnTime = returnTime;
            }

            // Additional methods to manage users list
            public void addUser(String userId) {
                users.add(userId);
            }

            public void removeUser(String userId) {
                users.remove(userId);
            }
        };
    }
}
