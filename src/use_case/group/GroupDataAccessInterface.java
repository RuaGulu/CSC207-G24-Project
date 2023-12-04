package use_case.group;

import entity.Group;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface GroupDataAccessInterface {
    ArrayList getGroups(String userName);

    boolean existsByName(String identifier);

    void addMember(String groupName, User user);

    void save(String groupName, Group group);
}