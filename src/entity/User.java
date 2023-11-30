package entity;

import java.util.List;

public interface User {
    String getUsername();
    String change_location();
    String getLocation();
    void addgroup(Group group);
    List<Group> getGroup();
}
