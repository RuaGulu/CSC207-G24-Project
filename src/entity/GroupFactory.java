package entity;

import java.time.LocalDateTime;

public interface GroupFactory {
    /** Require: member list is not empty**/
    Group create(String name, User[] member, LocalDateTime creationTime);
    }
