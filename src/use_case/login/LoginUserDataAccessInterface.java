package use_case.login;

import entity.CommonUser;
import entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);
    User get(String username);

    void save(User User);
}