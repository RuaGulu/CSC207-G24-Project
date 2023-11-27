package entity;

import java.time.LocalDateTime;

public interface Group {
    String getName();

    String getMembers();

    void addmember(User user);

    LocalDateTime getCreationTime();
}
