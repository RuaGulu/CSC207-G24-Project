package entity;

import java.time.LocalDateTime;

public interface Group {
    String getName();

    String getMembers();

    LocalDateTime getCreationTime();
    }
