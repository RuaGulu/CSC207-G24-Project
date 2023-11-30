package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface GroupFactory {
    /** Require: member list is not empty**/
    Group create(String name, String member, LocalDateTime creationTime);
    }
