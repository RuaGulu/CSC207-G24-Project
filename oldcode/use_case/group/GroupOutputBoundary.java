package use_case.group;

public interface GroupOutputBoundary {
    void prepareSuccessView(GroupOutputData group);

    void prepareFailView(String error);
}