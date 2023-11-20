package use_case.trip;

public interface TripOutputBoundary {
    void prepareSuccessView(TripOutputData tripOutputData);

    void prepareFailView(String error);
}
