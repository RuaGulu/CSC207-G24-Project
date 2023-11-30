package use_case.create_group;

import entity.User;

public interface GroupFindUserDataAccessInterface {
    boolean existsByName(String identifier);
    User get(String username);
    void updateuser(String username, User updatedUser);
}
