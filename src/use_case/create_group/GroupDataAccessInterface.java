package use_case.create_group;

import entity.Group;

public interface GroupDataAccessInterface {
    boolean existsByName(String identifier);
    void save(Group group);
    Group get(String group);
}

