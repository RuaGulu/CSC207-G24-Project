package use_case.create_group;

import use_case.login.LoginOutputData;

public interface CreateGroupOutputBoundary {
    void prepareSuccessView(CreateGroupOutputData group);

    void prepareFailView(String error);
}
