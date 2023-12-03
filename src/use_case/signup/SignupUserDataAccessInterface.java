package use_case.signup;

import entity.Group;
import entity.User;

public interface SignupUserDataAccessInterface {

    boolean existsByName(String identifier);

    boolean existsByGroup(String identifier);

    void save(User user);

    Group getGroup(String group);

}
