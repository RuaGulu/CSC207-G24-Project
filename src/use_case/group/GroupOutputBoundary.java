package use_case.group;

import use_case.login.LoginOutputData;

public interface GroupOutputBoundary {
    void prepareSuccessView(GroupOutputData group);

    void prepareFailView(String error);
}