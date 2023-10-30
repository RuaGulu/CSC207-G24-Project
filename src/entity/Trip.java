package entity;

import java.time.LocalDateTime;

public interface Trip {

    String getName();

    String getPassword();

    String getTripName();

    String[] getUser();

    LocalDateTime getCreationTime();
}
