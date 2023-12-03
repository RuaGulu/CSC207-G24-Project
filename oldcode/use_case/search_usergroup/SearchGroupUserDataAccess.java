package use_case.search_usergroup;

import entity.User;

public interface SearchGroupUserDataAccess {
    User get(String username);
    boolean existsByName(String identifier);
}
