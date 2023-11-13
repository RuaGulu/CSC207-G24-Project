package use_case.signup;

import entity.CommonUser;

public interface SignupUserDataAccessInterface {

    boolean existsByName(String identifier);

    void save(CommonUser user);

}
