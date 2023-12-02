package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface GroupFactory {
    /** Require: member list is not empty**/
    Group create(String name, ArrayList<User> members, LocalDateTime creationTime);
}