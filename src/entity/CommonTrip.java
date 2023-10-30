package entity;

import java.time.LocalDateTime;

public class CommonTrip implements Trip {
    private final String name;

    private final String password;

    private final String tripName;

    private final String[] user;

    private final LocalDateTime creationTime;


    CommonTrip(String name, String password, String tripName, String[] user, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.tripName = tripName;
        this.user = user;
        this.creationTime = creationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getTripName() {
        return tripName;
    }

    @Override
    public String[] getUser() {
        return new String[0];
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
