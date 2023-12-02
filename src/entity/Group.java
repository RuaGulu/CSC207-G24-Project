package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Group {
    String getName();

    ArrayList getMembers();
    void addMember(User user);

    LocalDateTime getCreationTime();

    boolean containUser(String userName);

    ArrayList getLocations();
}