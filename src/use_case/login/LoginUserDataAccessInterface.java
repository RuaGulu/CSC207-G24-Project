package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);
    User get(String username);
}