package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface Group {
    String getName();

    List getMembers();
    void addMember(User user);

    LocalDateTime getCreationTime();
}